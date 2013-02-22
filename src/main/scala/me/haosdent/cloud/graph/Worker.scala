package me.haosdent.cloud.graph

import akka.actor.Actor
import akka.event.Logging

class Worker extends Actor {
  val log = Logging(context.system, this)
  var state = Action.Uninit
  def receive = {
    case Action.Start =>
      state = Action.Start
      log.info(state + "")
      run()
    case Action.Ask =>
      log.info(state + "")
      sender.!(state)(context.parent)
  }

  def run() = {
    //TODO
    state = Action.Stop
    sender.!(state)(context.parent)
  }
}