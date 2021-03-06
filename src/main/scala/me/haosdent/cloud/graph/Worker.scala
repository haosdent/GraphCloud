package me.haosdent.cloud.graph

import akka.actor.Actor
import akka.event.Logging
import me.haosdent.cloud.graph.service.{Edges, Vertexs, Tasks}

class Worker extends Actor {
  val log = Logging(context.system, this)
  var state = Action.Uninit
  val taskService = new Tasks()
  val vertexService = new Vertexs()
  val edgeService = new Edges()

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
    while (taskService.isTaskEmpty()) {
      val taskList = taskService.getTaskList()
      taskList foreach {
        task =>
          val source = vertexService.getVertex(task.id)
          vertexService.initAsSource(source)
          val edgeList = edgeService.getEdgeList(source.id)
          edgeList foreach {
            edge =>
              val target = vertexService.getVertex(edge.targetId)
              //TODO
              //apply(gather(source, edge), target)
              val newTask = vertexService.finishAsTarget(target)
              if (newTask != null) {
                taskService.save(newTask)
              }
          }
      }
    }
    state = Action.Stop
    sender.!(state)(context.parent)
  }
}