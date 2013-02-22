package me.haosdent.cloud.graph

import akka.actor.{ActorSystem, ActorRef, Props, Actor}
import akka.routing.{Broadcast, RoundRobinRouter}
import akka.event.Logging

class Job extends Actor {

  val log = Logging(context.system, this)
  val size = 5
  val router :ActorRef = context.actorOf(Props[Worker].withRouter(RoundRobinRouter(size)), "router")
  var stopActors = Set.empty[ActorRef]

  override def preStart() {
    router ! Broadcast(Action.Start)
  }

  def receive = {
    case Action.Stop =>
      log.info(Action.Stop + "")
      stopActors = stopActors + sender
      router ! Broadcast(Action.Ask)
      context.become(checkFinish)
  }

  def checkFinish: Receive = {
    case Action.Stop =>
      stopActors = stopActors + sender
      if (stopActors.size == size) {
        context.stop(self)
        //FIXME
        context.system.shutdown()
      }
    case Action.Start =>
      log.info(Action.Start + "")
      stopActors foreach {
        actor => actor ! Action.Start
      }
      stopActors = Set.empty[ActorRef]
      context.unbecome()
  }
}

object Main extends App {
  val system = ActorSystem("system")
  var job = system.actorOf(Props[Job], name = "job")
}