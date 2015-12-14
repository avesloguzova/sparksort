/* SimpleApp.scala */

import org.apache.hadoop.fs.{Path, FileUtil, FileSystem}
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object SimpleApp extends App{
  val sourceFile = "file:///Volumes/Home/av/projects/spark-project/test.txt"
  val hdfsOutputDirectory = "temp-result"
  val mergedFileDestination = "output1.txt"
  val localFSDestination = "output.txt"

  val conf = new SparkConf().setMaster("local").setAppName("app")
  val sc = new SparkContext(conf)
  val hadoopConfig = sc.hadoopConfiguration
  val hdfs = FileSystem.get(hadoopConfig)


  sc.textFile(sourceFile).flatMap(_.split(' ')).map(_.toInt).sortBy(i => i).saveAsTextFile(hdfsOutputDirectory)
  val pathToMerged: Path = new Path(mergedFileDestination)
  FileUtil.copyMerge(hdfs, new Path(hdfsOutputDirectory), hdfs, pathToMerged, false, hadoopConfig, null)
  hdfs.copyToLocalFile(pathToMerged,new Path(localFSDestination))
}
