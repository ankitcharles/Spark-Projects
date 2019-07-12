package com.sparkTutorial.functions

//import com.allaboutscala.learn.spark.utils.Context
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by Nadim Bahadoor on 28/06/2016.
  *
  *  Tutorial: Learn How To Use Apache Spark
  *
  * [[http://allaboutscala.com/big-data/spark/]]
  *
  * Copyright 2016 Nadim Bahadoor (http://allaboutscala.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
  * use this file except in compliance with the License. You may obtain a copy of
  * the License at
  *
  *  [http://www.apache.org/licenses/LICENSE-2.0]
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
  * License for the specific language governing permissions and limitations under
  * the License.
  */
object Tutorial_08_SplitArrayColumn {

  def main(args: Array[String]): Unit = {


    // Setup
    val sparkConf = new SparkConf()
      .setAppName("Learn-Scala")
      .setMaster("local[*]")
      .set("spark.core.max", "2")

    val sparkSession = SparkSession
      .builder()
      .config(sparkConf)
      .getOrCreate()
    import sparkSession.sqlContext.implicits._

    val targets = Seq(("Plain Donut", Array(1.50, 2.0)), ("Vanilla Donut", Array(2.0, 2.50)), ("Strawberry Donut", Array(2.50, 3.50)))
    val df = sparkSession
      .createDataFrame(targets)
      .toDF("Name", "Prices")

    df.show()

    df.printSchema()

    val df2 = df
      .select(
        $"Name",
        $"Prices" (0).as("Low Price"),
        $"Prices" (1).as("High Price")
      )

    df2.show()
  }
}
