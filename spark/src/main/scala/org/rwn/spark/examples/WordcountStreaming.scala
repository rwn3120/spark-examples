package org.rwn.spark.examples

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming._
import org.rwn.spark.Constants._

/**
  * Created by Radek Strejc, radek.strejc at oracle.com on 10/31/18.
  */
object WordcountStreaming {
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
      // open socket stream (connects to your netcat server)
      val socketStreamDf = sparkSession.readStream
        .format("socket")
        .option("host", "127.0.0.1")
        .option("port", 12345)
        .load

      // adds implicit type conversions
      import sparkSession.implicits._

      // convert stream to text dataframe
      val textDf = socketStreamDf.as[String]

      val wordsDf = textDf.flatMap(value => value.split(" ")).filter(_.nonEmpty)

      val countDf = wordsDf.groupBy("value").count()

      val query = countDf.writeStream
        .format("console")
        .queryName("wordcount")
        .outputMode(OutputMode.Complete())
        .start()

      query.awaitTermination(ONE_MIN_IN_MILLIS)
    } finally {
      sparkContext.stop()
    }
  }
}
