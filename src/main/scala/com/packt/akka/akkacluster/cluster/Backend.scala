package com.packt.akka.akkacluster.cluster

import akka.actor.{Actor, ActorSystem, Props, RootActorPath}
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.MemberUp
import com.packt.akka.commons.{Add, BackendRegistration}
import com.typesafe.config.ConfigFactory

object Backend {
  def initiate(port: Int): Unit = {
    val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port") withFallback ConfigFactory.load().getConfig("Backend")
    val system = ActorSystem("ClusterSystem", config)
    val Backend = system.actorOf(Props[Backend], name = "Backend")
  }
}

class Backend extends Actor {
  val cluster = Cluster(context.system)

  override def preStart(): Unit = {
    cluster.subscribe(self, classOf[MemberUp])
  }

  override def postStop(): Unit = {
    cluster.unsubscribe(self)
  }

  def receive = {
    case Add(num1, num2) =>
      println(s"I'm a backend with path ${self} and I received add operation")
    case MemberUp(member) =>
      if (member.hasRole("frontend")) {
        context.actorSelection(RootActorPath(member.address) / "user" / "frontend") ! BackendRegistration
      }
  }
}