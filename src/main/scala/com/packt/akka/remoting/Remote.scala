package com.packt.akka.remoting

import akka.actor.{ActorSystem, Props}
import com.packt.akka.remoting.Worker.Work
import com.typesafe.config.ConfigFactory

object MemberService extends App {
  val config = ConfigFactory.load.getConfig("MemberService")
  val system = ActorSystem("MemberService", config)
  val worker = system.actorOf(Props[Worker], "remote-worker")
  worker ! Work("Test1: Work from member service")
  println(s"Worker actor path is ${worker.path}")
}

object MemberServiceLookup extends App {
  val config = ConfigFactory.load.getConfig("MemberServiceLookup")
  val system = ActorSystem("MemberServiceLookup", config)
  val worker = system.actorSelection("akka.tcp://MemberService@127.0.0.1:2552/user/remote-worker")
  worker ! Work("Test2: Work from member service lookup")
}

object MemberServiceRemoteCreation extends App {
  val config = ConfigFactory.load.getConfig("MemberServiceRemoteCreation")
  val system = ActorSystem("MemberServiceRemoteCreation", config)
  val worker = system.actorOf(Props[Worker], "workerRemoteActor")
  println(s"The remote path of the worker actor is ${worker.path}")
  worker ! Work("Test 3: Work from member service remote creation")
}