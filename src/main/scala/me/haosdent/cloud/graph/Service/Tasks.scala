package me.haosdent.cloud.graph.service

import me.haosdent.cloud.graph.model.Task
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.util.Bytes

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

  def parse(record: Result) = {
    val id = Bytes.toLong(record.getRow())
    new Task(id)
  }

  def save(task: Task) = {
    //TODO
    //Save to HBase
  }
}
