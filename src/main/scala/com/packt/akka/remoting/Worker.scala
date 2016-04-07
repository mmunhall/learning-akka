package com.packt.akka.remoting

import akka.actor.Actor

object Worker {
  case class Work(message: String)
}

class Worker extends Actor {
  import Worker._
  def receive = {
    case Work(msg) => println(s"""I received a Work message "$msg" and my actor ref is $self""")
  }
}
