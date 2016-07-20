package cl.smartketing.twitterclient.task.util

import org.apache.spark.{Accumulator, SparkContext}

import scala.collection.mutable

/**
  * @author daniel
  */
class MapCounting[T](val sc:SparkContext) extends Serializable {

  private val serialVersionUID = 1L

  private val mapCounting = mutable.Map.empty[T,Accumulator[Int]]

  def add(t:T,initialValue:Int=0) = mapCounting.put(t,sc.accumulator(initialValue))

  def foreach(fn:((T,Accumulator[Int]))=>Unit):Unit = {
    mapCounting.foreach(fn)
  }

  def get(t:T) = mapCounting.get(t)

  def increment(t:T,i:Int=1) = {
    try{
      get(t).get.add(i)
    }catch  {
      case e : Throwable => add(t,1) //create if not exist
    }

  }

  def toMap:Map[T,Int] = {
    val outputMap = mutable.Map.empty[T,Int]
    mapCounting.foreach((e:(T,Accumulator[Int]))=>{
      outputMap.put(e._1,e._2.value)
    })
    outputMap.toMap
  }

}
