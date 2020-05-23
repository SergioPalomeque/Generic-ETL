package com.generic.etl.extract

import org.apache.spark.sql.{DataFrame, SparkSession}

import scala.util.Try

trait Extract[A] {
  def read(configuration: A, spark: SparkSession): Try[DataFrame]
}
