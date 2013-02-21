package me.haosdent.cloud.graph

import akka.actor.{ActorSystem, ActorRef, Props, Actor}
import akka.routing.{Broadcast, RoundRobinRouter}

class Schedule extends Actor {

  val size = 5
  val router :ActorRef = context.actorOf(Props[Worker].withRouter(RoundRobinRouter(size)), "router")

  override def preStart() {
    router ! Broadcast(1)
  }

  def receive = {
    case _ => Unit
  }
}

object Main extends App {
  val system = ActorSystem("system")
  var schedule = system.actorOf(Props[Schedule], name = "schedule")
}


