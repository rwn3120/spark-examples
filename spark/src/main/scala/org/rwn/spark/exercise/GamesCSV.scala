package org.rwn.spark.exercise

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.rwn.spark.Constants._

object GamesCSV {

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
//      val gamesDF = sparkSession.read
//        .format("csv")
//        .option("header", "true")
//        .load("games.csv")
//
//      // TODO: create view
//      gamesDF.c_____OrR______TempView("games")
//
//      // TODO: create SQL query: show linux games only
//      gamesDF.sqlContext
//        .sql("S_____ name, is_free, metacritic_score F___ games W____ platforms_linux == true")
//        .show()
//    } finally {
//      sparkContext.stop()
//    }
  }
}
