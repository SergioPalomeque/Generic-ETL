package com.generic.etl

package object configurations {

  sealed trait ExecutionConfiguration
  case class Hive(database: String, table: String, partition: String) extends ExecutionConfiguration
  case class HiveQuery(query: String) extends ExecutionConfiguration
  case class FileHiveQuery(file: String) extends ExecutionConfiguration

}
