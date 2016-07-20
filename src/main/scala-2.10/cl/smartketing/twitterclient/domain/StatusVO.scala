package cl.smartketing.twitterclient.domain

import twitter4j.Status

/**
  * @author daniel
  */
case class StatusVO(s:Status) {

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

}