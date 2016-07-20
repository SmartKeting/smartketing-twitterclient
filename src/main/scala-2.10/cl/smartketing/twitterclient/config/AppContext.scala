package cl.smartketing.twitterclient.config


import org.apache.spark.SparkConf
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.api.java.JavaStreamingContext
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.twitter.TwitterUtils
import twitter4j.Status
import twitter4j.auth.AuthorizationFactory
import twitter4j.conf.ConfigurationBuilder

import scala.util.Properties


/**
  * @author daniel
  */
class AppContext {

  // create the spark configuration
  private val sparkConfig = new SparkConf()
    .setMaster("local[2]")
    .setAppName("SmartKetingCore")

  // the SparkStreamingContext
  private val ssc = new JavaStreamingContext(sparkConfig, Seconds(20))

  // create twitter authorization
  private val auth = AuthorizationFactory.getInstance(
    new ConfigurationBuilder().setDebugEnabled(true)
    .setOAuthConsumerKey(Properties.envOrElse("TWITTER_CONSUMER", ""))
    .setOAuthConsumerSecret(Properties.envOrElse("TWITTER_CONSUMER_SECRET", ""))
    .setOAuthAccessToken(Properties.envOrElse("TWITTER_TOKEN", ""))
    .setOAuthAccessTokenSecret(Properties.envOrElse("TWITTER_TOKEN_SECRET", ""))
    .build()
  )


  /**
    * Get a TwitterStream
    * @param filters
    * @return
    */
  def getTwitterStream(filters:Array[String]):ReceiverInputDStream[Status] = {
    TwitterUtils.createStream(ssc,auth,filters).receiverInputDStream
  }

  /**
    * Start the SparkStreamingContext
    */
  def start = {
    ssc.start
    ssc.awaitTermination
  }

  /**
    * Stop the SparkStreamingContext
    */
  def stop = ssc.stop()



}
