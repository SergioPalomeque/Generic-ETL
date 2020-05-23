package com.generic.etl.load

import com.generic.etl.configurations.{ExecutionConfiguration, Hive}
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

import scala.util.Try


object Writers {

  def apply[A <: ExecutionConfiguration](implicit loader: Load[A]) = loader

  implicit val csv = new Load[String] {
    def write(path: String, data: DataFrame, spark: SparkSession): Try[Unit] = {
      Try(data.write.csv(path))
    }
  }

  implicit val hive = new Load[Hive] {
    def write(config: Hive, data: DataFrame, spark: SparkSession): Try[Unit] = {
      Try {
        data.write.mode(SaveMode.Overwrite).saveAsTable(s"${config.database}.${config.table}")
      }
    }
  }

}
