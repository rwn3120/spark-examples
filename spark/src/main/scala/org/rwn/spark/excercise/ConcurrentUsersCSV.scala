package org.rwn.spark.excercise

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
//      val linesRDD = sparkContext.t___F__e("users.csv")     // [ binary data ] => array["First line", "Second line"] = RDD["1540886311000,9480521", "1540886596000,9521979", ...]
//
//      val userStatsRDD = linesRDD.map(line => {             // RDD["1540886311000,9480521", "1540886596000,9521979", ...] => RDD[('2018-10-31 15:03:56', 9480521), ('2018-10-31 08:42:01', 9521979), ...]
//        val splits = line.split(',')
//        val timeInMillis = splits(0).toLong
//        val concurrentUsers = splits(1).toLong
//        (new Date(timeInMillis), concurrentUsers)
//      })
//
//      val yesterday = new Date(System.currentTimeMillis() - ONE_DAY_IN_MILLIS)
//      // TODO: filter last 24h
//      val latestUserStatsRDD = userStatsRDD.f____r {
//        case (date, _) =>
//          date.after(yesterday)
//      }
//
//      // TODO: group by hour
//      val hourlyPeaksRDD = latestUserStatsRDD               // mapRDD["2018-10-31 15:03:56":('2018-10-31 15:03:56', 9480521), ...), "2018-10-31 15:03:56":('2018-10-31 08:00:00', 9521979), ...), ...]
//        .g____B_ {
//          case (date, _) => {
//            DateUtils.round(date, Calendar.HOUR);
//          }
//        }
//        .map {
//          case (date, concurrentUsers) => {                 // mapRDD["2018-10-31 15:03:56":('2018-10-31 15:03:56', 9480521), ...), "2018-10-31 15:03:56":('2018-10-31 08:00:00', 9521979), ...), ...]
//                                                            //                  => RDD[('2018-10-31 15:03:56', MAX(9480521,...), ('2018-10-31 08:00:00', MAX(9521979, ....)]
//            // TODO: get max
//            var max = _L
//            for (users <- concurrentUsers) {
//              if (users._2 > max) {
//                max = users._2
//              }
//            }
//            (date, max)
//          }
//        }
//
//      val hourlyPeaks = hourlyPeaksRDD.collect().sortBy(hourlyPeak => hourlyPeak ._1)   // RDD[('2018-10-31 15:03:56', MAX(9480521,...), ('2018-10-31 08:00:00', MAX(9521979, ....)]
//                                                                                        //                => array[('2018-10-31 15:03:56', MAX(9480521,...), ('2018-10-31 08:00:00', MAX(9521979, ....)] as peaks
//
//      // TODO: print results
//      hourlyPeaks.f__e_ch(println)                 // for (hourlyPeak <- hourlyPeaks) { println(hourlyPeak) }
//                                                   // hourlyPeaks.foreach(hourlyPeak => println(hourlyPeak))
//                                                   // hourlyPeaks.foreach(println(_))
//    } finally {
//      sparkContext.stop()
//    }
  }
}
