package me.haosdent.cloud.graph

import akka.actor.Actor

class Worker extends Actor {
  def receive = {
    case msg => println("Received message '%s' in actor %s".format(msg, self.path.name))
  }
}