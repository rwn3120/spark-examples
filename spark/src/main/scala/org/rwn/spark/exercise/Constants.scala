package org.rwn.spark.exercise

/**
  * Created by Radek Strejc, radek.strejc at oracle.com on 10/29/18.
  */
object Constants {
  val SPARK_MASTER_PROP = "spark.master"
  val LOCAL = "local[*]"

  val ONE_SEC_IN_MILLIS = 1000
  val ONE_MIN_IN_MILLIS = 60 * ONE_SEC_IN_MILLIS
  val ONE_HOUR_IN_MILLIS = 60 * ONE_MIN_IN_MILLIS
  val ONE_DAY_IN_MILLIS = 24 * ONE_HOUR_IN_MILLIS
}
