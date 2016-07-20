package cl.smartketing.twitterclient.data

import cl.smartketing.twitterclient.data.domain.AbstractVO
import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import org.bson.Document

import scala.reflect.ClassTag

/**
  * @author daniel
  */
object Dao {

  private val mongoClient = new MongoClient()
  private val database = mongoClient.getDatabase("smartketing")

  private def collection(collectionName:String):MongoCollection[Document] = {
    database.getCollection(collectionName)
  }

  def insert(obj:AbstractVO) = {
    val collectionName = obj.getClass.getSimpleName
    collection(collectionName).insertOne(obj.toDocument)
  }


}
