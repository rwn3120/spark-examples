package org.rwn.spark.exercise

import java.util.{Calendar, Date}

import org.apache.commons.lang.time.DateUtils
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.rwn.spark.Constants._

object ConcurrentUsersCSV {

  def main(args: Array[String]): Unit = {
//    val appName = this.getClass.getCanonicalName
//
//    println(s"Starting ${appName}")
//
//    // get configuration
//    val conf = new SparkConf().setAppName(appName)
//    if (conf.get(SPARK_MASTER_PROP, "") == "") {
//      System.out.println(
//        s"Property ${SPARK_MASTER_PROP} is not set. Using ${LOCAL}")
//      conf.setMaster(LOCAL)
//    }
//
//    val sparkSession = SparkSession.builder.config(conf).getOrCreate()
//    val sparkContext = sparkSession.sparkContext
//
//    try {
//      // TODO: load csv file as text file
//      val linesRDD = sparkContext.___("users.csv")
//
//      val userStatsRDD = linesRDD.map(line => {
//        val splits = line.split(',')
//        val timeInMillis = splits(0).toLong
//        val concurrentUsers = splits(1).toLong
//        (new Date(timeInMillis), concurrentUsers)
//      })
//
//      val yesterday = new Date(System.currentTimeMillis() - ONE_DAY_IN_MILLIS)
//      // TODO: filter last 24h
//      val latestUserStatsRDD = userStatsRDD.___ {
//        case (date, _) =>
//          println(date.after(yesterday))
//          date.after(yesterday)
//      }
//
//      // TODO: group by hour
//      val hourlyPeaksRDD = latestUserStatsRDD
//        .___ {
//          case (date, _) => {
//            DateUtils.round(date, Calendar.HOUR);
//          }
//        }
//        .map {
//          case (date, concurrentUsers) => {
//            // TODO: get max
//            var max = ___
//            (date, max)
//          }
//        }
//
//      val hourlyPeaks = hourlyPeaksRDD.collect().sortBy(_._1)
//
//      // TODO: print results
//      hourlyPeaks.___(println)
//    } finally {
//      sparkContext.stop()
//    }
  }
}
