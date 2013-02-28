package me.haosdent.cloud.graph.service

import me.haosdent.cloud.graph.model.{Task, Vertex}

class Vertexs {
  def getVertex(id: Long) : Vertex = {
    //TODO
    null
  }

  def initAsSource(vertex: Vertex) = {
    //TODO
    //1.update value(value = result)
    //2.reset result(result = 0)
    //3.save
    save(vertex)
  }

  def finishAsTarget(vertex: Vertex) : Task = {
    //TODO
    vertex.curIn += 1
    if (vertex.curIn == vertex.in) {
      vertex.iter += 1
      vertex.curIn = 0
      //FIXME
      val limit = 0
      if (vertex.iter <= limit) {
        val task = new Task(vertex.id)
        return task
      }
    }
    save(vertex)
    null
  }

  def save(vertex: Vertex) = {
    //TODO
    //save to HBase
  }
}
