package com.packt.akka.playwithactors

import akka.actor.{Actor, ActorSystem, Props}
import com.packt.akka.playwithactors.MusicController.{Play, Stop}
import com.packt.akka.playwithactors.MusicPlayer.{StartMusic, StopMusic}

// Music Controller Messages
object MusicController {
  sealed trait ControllerMsg
  case object Play extends ControllerMsg
  case object Stop extends ControllerMsg
  def props = Props[MusicController]
}

// Music Controller
class MusicController extends Actor {
  def receive = {
    case Play => println("Music Started...")
    case Stop => println("Music Stopped...")
  }
}

// Music Player Messages
object MusicPlayer {
  sealed trait PlayMsg
  case object StartMusic extends PlayMsg
  case object StopMusic extends PlayMsg
}

// Music Player
class MusicPlayer extends Actor {
  def receive = {
    case StopMusic => println("I don't want to stop music...")
    case StartMusic =>
      val controller = context.actorOf(MusicController.props, "controller")
      controller ! Play
    case _ => println("Unknown message...")
  }
}

object Creation extends App {
  val system = ActorSystem("creation")
  val player = system.actorOf(Props[MusicPlayer], "player")
  player ! StartMusic
  system.terminate()
}