package com.packt.akka.actorpath

import akka.actor.{Actor, ActorIdentity, ActorRef, Identify}

class Watcher extends Actor {

  var counterRef: ActorRef = _
  val selection = context.actorSelection("/user/counter")

  selection ! Identify(None)

  def receive = {
    case ActorIdentity(_, Some(ref)) =>
      println(s"Actor Reference for counter is $ref")
    case ActorIdentity(_, None) =>
      println(s"Actor selection for actor doesn't live")
  }
}
