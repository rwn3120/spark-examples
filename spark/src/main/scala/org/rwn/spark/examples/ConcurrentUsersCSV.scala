package org.rwn.spark.examples

import java.util.{Calendar, Date}

import org.apache.commons.lang.time.DateUtils
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.rwn.spark.Constants._

object ConcurrentUsersCSV {

  def main(args: Array[String]): Unit = {
    val appName = this.getClass.getCanonicalName

    println(s"Starting ${appName}")

    // get configuration
    val conf = new SparkConf().setAppName(appName)
    if (conf.get(SPARK_MASTER_PROP, "") == "") {
      System.out.println(
        s"Property ${SPARK_MASTER_PROP} is not set. Using ${LOCAL}")
      conf.setMaster(LOCAL)
    }

    val sparkSession = SparkSession.builder.config(conf).getOrCreate()
    val sparkContext = sparkSession.sparkContext

    try {
      val linesRDD = sparkContext.textFile("users.csv")

      // []string  => [](Date, Long)
      val userStatsRDD = linesRDD.map(line => {
        val splits = line.split(',')
        val timeInMillis = splits(0).toLong
        val concurrentUsers = splits(1).toLong
        (new Date(timeInMillis), concurrentUsers)
      })

      val yesterday = new Date(System.currentTimeMillis() - ONE_DAY_IN_MILLIS)
      val latestUserStatsRDD = userStatsRDD.filter {
        case (date, _) =>
          date.after(yesterday)
      }

      val hourlyPeaksRDD = latestUserStatsRDD
        .groupBy {
          case (date, _) => {
            DateUtils.round(date, Calendar.HOUR);
          }
        }
        .map {
          case (date, concurrentUsers) => {
            var max = 0L
            for (users <- concurrentUsers) {
              if (users._2 > max) {
                max = users._2
              }
            }
            (date, max)
          }
        }

      val hourlyPeaks = hourlyPeaksRDD.collect().sortBy(peak => peak._1)

      hourlyPeaks.foreach(println)
    } finally {
      sparkContext.stop()
    }
  }
}
