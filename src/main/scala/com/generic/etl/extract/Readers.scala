package com.generic.etl.extract

import com.generic.etl.configurations.{ExecutionConfiguration, FileHiveQuery, HiveQuery}
import org.apache.spark.sql.{DataFrame, SparkSession}

import scala.io.Source
import scala.util.Try

object Readers {


  def apply[A <: ExecutionConfiguration](implicit reader: Extract[A]) = reader

  implicit val csv = new Extract[String] {
    def read(file: String, spark: SparkSession): Try[DataFrame] = {
      Try(spark.read.csv(file))
    }
  }

  implicit val fileHiveQuery = new Extract[FileHiveQuery] {
    def read(config: FileHiveQuery, spark: SparkSession): Try[DataFrame] = {
      import spark.sql

      Try {
        val query = Source.fromFile(config.file).getLines.mkString
        sql(query)
      }

    }
  }

  implicit val hiveQuery = new Extract[HiveQuery] {
    def read(configuration: HiveQuery, spark: SparkSession): Try[DataFrame] = {
      import spark.sql

      Try(sql(configuration.query))
    }
  }

}
