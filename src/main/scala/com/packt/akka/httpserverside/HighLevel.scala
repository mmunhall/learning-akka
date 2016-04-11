package com.packt.akka.httpserverside

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._

import scala.io.StdIn

object HighLevel extends App {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val route =
    path("") {
      get {
        println("Received request")
        complete("Hello Akka HTTP Server Side - High Level")
      }
    }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8888)

  println(s"Server online at http://localhost:8888\nPress RETURN to stop.")
  StdIn.readLine()

  bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
}