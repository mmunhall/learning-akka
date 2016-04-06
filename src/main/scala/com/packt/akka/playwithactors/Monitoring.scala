package com.packt.akka.playwithactors

import akka.actor.{Actor, ActorRef, ActorSystem, Props, Terminated}

class Ares(athena: ActorRef) extends Actor {

  override def preStart() = {
    context.watch(athena)
  }

  override def postStop() = {
    println("Ares postStop...")
  }

  def receive = {
    case Terminated => context.stop(self);
  }
}

class Athena extends Actor {

  def receive = {
    case msg =>
      println(s"Athena received $msg")
      context.stop(self)
  }
}

object Monitoring extends App {
  val system = ActorSystem("monitoring")
  val athena = system.actorOf(Props[Athena], "athena")
  val ares = system.actorOf(Props(classOf[Ares], athena), "ares")

  athena ! "hi!"

  system.terminate()
}