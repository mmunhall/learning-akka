name := "learning-akka"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.0",
  "com.typesafe.akka" %% "akka-persistence" % "2.4.0",
  "org.iq80.leveldb" % "leveldb" % "0.7",
  "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8",
  "com.typesafe.akka" %% "akka-persistence-query-experimental" % "2.4.0",
  "com.typesafe.akka" % "akka-stream-experimental_2.11" % "1.0",
  "com.typesafe.akka" %% "akka-remote" % "2.4.0",
  "com.typesafe.akka" %% "akka-cluster" % "2.4.0",
  "com.typesafe.akka" %% "akka-cluster-tools" % "2.4.0",
  "com.typesafe.akka" %% "akka-cluster-sharding" % "2.4.0",
  "com.typesafe.akka" %% "akka-http-core-experimental" % "1.0",
  "com.typesafe.akka" %% "akka-http-experimental" % "1.0",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "1.0",
  "org.reactivemongo" %% "reactivemongo" % "0.11.7",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "com.typesafe.akka" %% "akka-http-testkit-experimental" % "1.0"
)

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
resolvers += "Typesafe" at "https://repo.typesafe.com/typesafe/releases/"