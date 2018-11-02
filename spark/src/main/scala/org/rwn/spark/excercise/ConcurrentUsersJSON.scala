package org.rwn.spark.excercise

import java.net.URL
import java.nio.charset.Charset
import java.util.{Calendar, Date}

import org.apache.commons.io.IOUtils
import org.apache.commons.lang.time.DateUtils
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.rwn.spark.Constants._

import scala.collection.mutable

/**
  * Created by Radek Strejc, radek.strejc at oracle.com on 10/29/18.
  */
object ConcurrentUsersJSON {

  def timestamp(millis: Long): java.sql.Timestamp =
    new java.sql.Timestamp(millis)
  def timestamp(date: Date): java.sql.Timestamp = timestamp(date.getTime)

  def main(args: Array[String]): Unit = {
//    val appName = this.getClass.getCanonicalName
//
//    // get input
//    val input = if (args.nonEmpty) {
//      args(0)
//    } else {
//      throw new IllegalArgumentException("Missing argument")
//    }
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
//    val json = IOUtils.toString(
//      new URL("https://store.steampowered.com/stats/userdata.json"),
//      Charset.forName("utf8"))
//
//    val sparkSession = SparkSession.builder.config(conf).getOrCreate()
//    // TODO: get SparkSQL context
//    val sparkSQLContext = sparkSession.sqlC_____t
//
//    try {
//      import sparkSession.implicits._
//      val jsonDS = Seq(json).toDS
//      // TODO: read json
//      val jsonDF = sparkSQLContext.r__d.j_n(jsonDS)           // string =>
//
//      var statsDF = jsonDF
//        .flatMap(
//          _.getAs[mutable.WrappedArray[mutable.WrappedArray[Long]]]("data"))
//        .map(row => (timestamp(row(0)), row(1)))
//
//      statsDF = statsDF.map {
//        case (ts, users) => {
//          val roundedTimestamp =
//            timestamp(DateUtils.round(new Date(ts.getTime), Calendar.HOUR))
//          (roundedTimestamp, users)
//        }
//      }
//
//      // TODO: create SQL query: show hourly peaks
//      statsDF.toDF("date", "users").createOrReplaceTempView("stats")
//
//      // TODO: create SQL query: show hourly peaks
//      sparkSQLContext
//        .sql("""SELECT ___""")
//        .show
//    } finally {
//      sparkSQLContext.sparkContext.stop()
//    }
  }
}
