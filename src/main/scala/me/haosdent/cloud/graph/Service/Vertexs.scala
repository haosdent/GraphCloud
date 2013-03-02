package me.haosdent.cloud.graph.service

import me.haosdent.cloud.graph.model.{Task, Vertex}
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.util.Bytes

class Vertexs {

  val cfBytes = Bytes.toBytes("cf")
  val inBytes = Bytes.toBytes("in")
  val curInBytes = Bytes.toBytes("cur")
  val outBytes = Bytes.toBytes("out")
  val valueBytes = Bytes.toBytes("value")
  val collectBytes = Bytes.toBytes("collect")
  val iterBytes = Bytes.toBytes("iter")

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

  def parse[V, C](record: Result, toValue: Array[Byte] => V, toCollect: Array[Byte] => C) : Vertex[V, C] = {
    val id = Bytes.toLong(record.getRow())
    val in = Bytes.toInt(record.getValue(cfBytes, inBytes))
    val curIn = Bytes.toInt(record.getValue(cfBytes, curInBytes))
    val out = Bytes.toInt(record.getValue(cfBytes, outBytes))
    val value = toValue(record.getValue(cfBytes, valueBytes))
    val collect = toCollect(record.getValue(cfBytes, collectBytes))
    val iter = Bytes.toInt(record.getValue(cfBytes, iterBytes))
    new Vertex(id, in, curIn, out, value, collect, iter)
  }

  def save(vertex: Vertex) = {
    //TODO
    //save to HBase
  }
}
