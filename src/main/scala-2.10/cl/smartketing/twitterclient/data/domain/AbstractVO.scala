package cl.smartketing.twitterclient.data.domain

import org.bson.Document

/**
  * @author daniel
  */
abstract class AbstractVO {

  def toDocument:Document

}
