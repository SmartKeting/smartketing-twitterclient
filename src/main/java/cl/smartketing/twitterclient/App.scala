package cl.smartketing.twitterclient

import cl.smartketing.twitterclient.task.Task

/**
  * @author daniel
  */
object App {



  def main(args: Array[String]): Unit = {

    //define tasks as array
    val tasks = List(
      new Task(Array("apple")),
      new Task(Array("pear"))
    )

    doTheProcess("the_RDD", tasks)

  }


  /**
    * run process for every task.
    * @param rdd
    */
  def doTheProcess(rdd:String, tasks:List[Task]): Unit = {
    for(task <- updatedTasks(tasks))
      task.process(rdd)
  }


  /**
    * Get a updated list of registered tasks
    * @param tasks
    */
  def updatedTasks(tasks:List[Task]): List[Task] = {
    //TODO : ask to db and update tasks....
    val newTask = new Task(Array("keyword"))


    tasks :+ newTask
  }


}
