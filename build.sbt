
// SmartKeting - TwitterClient

name := "smartketing-twitterclient"
version := "1.0"
scalaVersion := "2.10.4"


// Dependencies

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.2"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.6.2"
libraryDependencies += "org.apache.spark" %% "spark-streaming-twitter" % "1.6.2"

libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.4"

libraryDependencies += "org.mongodb" % "mongo-java-driver" % "3.2.2"
