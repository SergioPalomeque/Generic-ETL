package com.generic.etl.load

import org.apache.spark.sql.{DataFrame, SparkSession}

import scala.util.Try

trait Load[A] {
  def write(configuration: A, data: DataFrame, spark: SparkSession): Try[Unit]
}
