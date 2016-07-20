package cl.smartketing.twitterclient.task


import cl.smartketing.twitterclient.data.Dao
import cl.smartketing.twitterclient.data.domain.StatusVO
import org.apache.spark.rdd.RDD
import twitter4j.Status

/**
  * @author daniel
  */
class Task (
             private val keywords: Array[String]
           )
{

  def process(rdd:RDD[Status]) = {
    rdd
      //enable distributed cache
      .cache()

      //filter by language
      .filter(s => s.getLang.matches("es") && !s.isRetweet)

      //4 each partition..
      .foreachPartition(

      //4 each tweet...
        _.foreach(status => {

          //create the domain object
          val newStatus = new StatusVO(status)
          println(status)

          //persist
          Dao.insert(newStatus)

        })
      )
  }


}
