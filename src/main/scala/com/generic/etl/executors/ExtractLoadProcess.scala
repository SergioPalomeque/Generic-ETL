package com.generic.etl.executors

import com.generic.etl.extract.Extract
import com.generic.etl.load.Load
import org.apache.spark.sql.SparkSession

class ExtractLoadProcess[A, B](read: A, write: B)(implicit reader: Extract[A], writer: Load[B]) {
  def execute(spark: SparkSession): Unit = {
    for {
      data <- reader.read(read, spark)
      _ <- writer.write(write, data, spark)
    } ()
  }
}
