package com.packt.akka.playwithactors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.packt.akka.playwithactors.Checker.{BlackUser, CheckUser, WhiteUser}
import com.packt.akka.playwithactors.Recorder.NewUser
import com.packt.akka.playwithactors.Storage.AddUser

import scala.concurrent.duration._
import scala.language.postfixOps

case class User(username: String, email: String)

object Recorder {
  sealed trait RecorderMsg
  case class NewUser(user: User) extends RecorderMsg

  def props(checker: ActorRef, storage: ActorRef) = {
    Props(new Recorder(checker, storage))
  }
}

object Checker {
  sealed trait CheckerMsg
  case class CheckUser(user: User) extends CheckerMsg

  sealed trait CheckerResponse
  case class BlackUser(user: User) extends CheckerResponse
  case class WhiteUser(user: User) extends CheckerResponse
}

object Storage {
  sealed trait StorageMsg
  case class AddUser(user: User) extends StorageMsg
}

class Storage extends Actor {
  var users = List.empty[User]

  def receive = {
    case AddUser(user) =>
      println(s"Storage: $user added")
      users = user :: users
  }
}

class Checker extends Actor {
  val blackList = List(User("Adam", "adam@gmail.com"))

  def receive = {
    case CheckUser(user) if blackList.contains(user) =>
      println(s"Checker: $user in the blacklist")
      sender() ! BlackUser(user)
    case CheckUser(user) =>
      println(s"Checker: $user not in the blacklist")
      sender() ! WhiteUser(user)
  }
}

class Recorder(checker: ActorRef, storage: ActorRef) extends Actor {
  import scala.concurrent.ExecutionContext.Implicits.global

  implicit val timeout = Timeout(5 seconds)

  def receive = {
    case NewUser(user) =>
      checker ? CheckUser(user) map {
        case WhiteUser(user) =>
          storage ! AddUser(user)
        case BlackUser(user) =>
          println(s"Recorder: $user in the blacklist")
      }
  }
}

object TalkToActor extends App {
  val system = ActorSystem("talk-to-actor")
  val checker = system.actorOf(Props[Checker], "checker")
  val storage = system.actorOf(Props[Storage], "storage")
  val recorder = system.actorOf(Recorder.props(checker, storage), "recorder")
  recorder ! Recorder.NewUser(User("Jon", "jon@packt.com"))
  Thread.sleep(100)
  system.terminate()

}
