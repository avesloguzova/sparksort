Part of Scala HW #7

Installation on OSX:
http://datahugger.org/datascience/setting-up-hadoop-v2-with-spark-v1-on-osx-using-homebrew/

Build:
sbt package

Run:
{SPARK_HOME_DIRECTORY}/bin/spark-submit --class "SimpleApp" --master local[4] target/scala-2.10/scala-hw_2.10-1.0.jar


