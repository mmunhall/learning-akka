package com.packt.akka.restapi.db

import com.packt.akka.restapi.models._
import scala.concurrent.ExecutionContext
import reactivemongo.bson.{BSONDocument, BSONObjectID}
import reactivemongo.api.collections.bson.BSONCollection

object TweetManager {
  import MongoDB._
  import TweetEntity._

  val collection = db[BSONCollection]("tweets")

  def save(tweetEntity: TweetEntity)(implicit ec: ExecutionContext) = collection.insert(tweetEntity).map(_ => Created(tweetEntity.id.stringify))

  def findById(id: String)(implicit ec: ExecutionContext) = collection.find(queryById(id)).one[TweetEntity]

  def deleteById(id: String)(implicit ec: ExecutionContext) = collection.remove(queryById(id)).map(_ => Deleted)

  def find(implicit ec: ExecutionContext) = collection.find(emptyQuery).cursor[BSONDocument].collect[List]()

  private def emptyQuery = BSONDocument()

  private def queryById(id: String) = BSONDocument("_id" -> BSONObjectID(id))

}