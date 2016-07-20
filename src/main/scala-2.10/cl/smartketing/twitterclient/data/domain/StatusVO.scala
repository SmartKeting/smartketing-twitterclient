package cl.smartketing.twitterclient.data.domain

import org.bson.Document
import twitter4j.Status

/**
  * @author daniel
  */
case class StatusVO(s:Status) extends AbstractVO{

  val createdAt = s.getCreatedAt
  val text = s.getText
  val favorited = s.getFavoriteCount
  val retweeted = s.getRetweetCount
  val place = if (s.getPlace != null) s.getPlace.getFullName else ""

  val userId = s.getUser.getScreenName
  val userName = s.getUser.getName
  val userDescription = s.getUser.getDescription
  val userTimezone = s.getUser.getTimeZone
  val userFriends = s.getUser.getFriendsCount
  val userStatuses = s.getUser.getStatusesCount
  val userFollowers = s.getUser.getFollowersCount

  val profileBgColor = s.getUser.getProfileBackgroundColor


  //TODO: create a generic method toDocument
  override def toDocument = new Document()
    .append("createdAt",createdAt)
    .append("text",text)
    .append("favorited",favorited)
    .append("retweeted",retweeted)
    .append("place",place)

    .append("userId",userId)
    .append("userName",userName)
    .append("userDescription",userDescription)
    .append("userTimezone",userTimezone)
    .append("userFriends",userFriends)
    .append("userStatuses",userStatuses)
    .append("userFollowers",userFollowers)

    .append("profileBgColor",profileBgColor)


}