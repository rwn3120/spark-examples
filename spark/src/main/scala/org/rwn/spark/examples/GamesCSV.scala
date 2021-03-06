package org.rwn.spark.examples

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.rwn.spark.Constants._

object GamesCSV {

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
      val gamesDF = sparkSession.read
        .format("csv")
        .option("header", "true")
        .load("games.csv")

      gamesDF.createOrReplaceTempView("games")

      gamesDF.sqlContext
        .sql(
          "SELECT name, is_free, metacritic_score FROM games WHERE platforms_linux == true")
        .show()
    } finally {
      sparkContext.stop()
    }
  }
}
