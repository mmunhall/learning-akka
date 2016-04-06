package com.packt.akka.actorpath

import akka.actor.{ActorSystem, PoisonPill, Props}

object Watch extends App {
//  val system = ActorSystem("Watch-actor-selection")
//  val counter1 = system.actorOf(Props[Counter], "counter")
//  println(s"Actor Reference for counter1: $counter1")
//  val counterSelection1 = system.actorSelection("counter")
//  println(s"Actor Selection for counter1: $counterSelection1")
//  counter1 ! PoisonPill
//  Thread.sleep(100)
//  val counter2 = system.actorOf(Props[Counter], "counter")
//  println(s"Actor Reference for counter2: $counter2")
//  val counterSelection2 = system.actorSelection("counter")
//  println(s"Actor Selection for counter2: $counterSelection2")

  val system = ActorSystem("Watch-actor-selection")
  val counter = system.actorOf(Props[Counter], "counter")
  val watcher = system.actorOf(Props[Watcher], "watcher")

  Thread.sleep(1000)
  system.terminate()
}
