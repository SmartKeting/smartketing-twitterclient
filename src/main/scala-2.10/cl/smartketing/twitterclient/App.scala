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

    //init app and streaming context
    val appContext = new AppContext()

    //get streaming ctx and set operations
    appContext
        .getTwitterStream(Array("apple"))
        .foreachRDD(doTheProcess(_))

    //lets go!!
    appContext.start

    //appContext.stop

  }


  /**
    * run process for every task.
    *
    * @param rdd
    */
  def doTheProcess(rdd:RDD[Status]) = {
    for(task <- updatedTasks())
      task.process(rdd)
  }


  /**
    * Get a updated list of registered tasks
    */
  def updatedTasks(): List[Task] = {
    //TODO : ask to db and update tasks....
    //define tasks as array
    val tasks = List(
      new Task(Array("apple"))
    )

    //val newTask = new Task(Array("keyword"))
    //tasks :+ newTask

    tasks
  }


}
