package cl.smartketing.twitterclient.task


import cl.smartketing.twitterclient.domain.StatusVO
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
      .cache()
      .filter(s => s.getLang.matches("es") && !s.isRetweet)
      .foreachPartition(
        _.foreach(status => {
          val newStatus = new StatusVO(status)
          //StatusDao.insert(new StatusVO(status).toDocument)
          println(status)
        })
      )
  }


}
