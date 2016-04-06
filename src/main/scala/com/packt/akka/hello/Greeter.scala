package com.packt.akka.hello

import akka.actor.Actor

/**
  * Created by mikemunhall on 3/29/16.
  */
case class Greeter() extends Actor {
  def receive = {
    case WhoToGreet(who) => println(s"Hello $who")
  }
}
