package com.packt.akka.persistentfsm

import akka.actor.{ActorSystem, Props}

object PersistentFSM extends App {
  import Account._
  val system = ActorSystem("persistent-fsm-actors")
  val account = system.actorOf(Props[Account])
  account ! Operation(1000, CR)
  account ! Operation(10, DR)
  account ! Operation(50, CR)
  account ! Operation(20, DR)
  account ! Operation(3000, DR)
  Thread.sleep(1000)
  system.terminate()
}
