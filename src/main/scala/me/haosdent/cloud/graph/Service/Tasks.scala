package me.haosdent.cloud.graph.service

import me.haosdent.cloud.graph.model.Task

class Tasks {
  def isTaskEmpty() = {
    //TODO
    true
  }

  def getTaskList() : List[Task] = {
    //TODO
    //1. Get tasks
    //2. Hide tasks
    //3. When failed
    List.empty[Task]
  }

  def save(task: Task) = {
    //TODO
    //Save to HBase
  }
}
