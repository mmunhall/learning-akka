package com.packt.akka.routes

import akka.actor.Actor

/**
  * Created by mikemunhall on 3/29/16.
  */
class Worker extends Actor {
  import Worker._

  def receive = {
    case _: Work =>
      println(s"I received Work message and my ActorRef is $self")
  }
}


object Worker {
  case class Work()
}