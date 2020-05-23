package com.generic.etl

import com.generic.etl.executors.ExtractLoadProcess
import com.generic.etl.extract.Readers
import com.generic.etl.load.Writers
import org.apache.spark.sql.SparkSession


object App extends App {

  import Readers._
  import Writers._
  import configurations._

  val spark = SparkSession
    .builder()
    .appName("Generic ETL process")
    .master("local[*]")
    .enableHiveSupport()
    .getOrCreate()


  new ExtractLoadProcess("", Hive("", "", "")).execute(spark)

  new ExtractLoadProcess(FileHiveQuery(""), Hive("", "", "")).execute(spark)


}
