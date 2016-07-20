package cl.smartketing.twitterclient

import cl.smartketing.twitterclient.config.AppContext
import cl.smartketing.twitterclient.task.Task
import org.apache.spark.rdd.RDD
import twitter4j.Status

/**
  * @author daniel
  */
object App {



  def main(args: Array[String]): Unit = {

    //define the params
    val params = if(args.length>0) args else Array("dynamodb","aws")

    //init app and streaming context
    val appContext = new AppContext()

    //get streaming ctx and set operations
    appContext
        .getTwitterStream(params)
        .foreachRDD(Task.process(_))

    //lets go!!
    appContext.start

    //appContext.stop

  }



}
