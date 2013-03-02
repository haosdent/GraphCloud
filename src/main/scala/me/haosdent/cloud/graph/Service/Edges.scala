package me.haosdent.cloud.graph.service

import me.haosdent.cloud.graph.model.Edge
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.client.Result

class Edges {

  val cfBytes = Bytes.toBytes("cf")
  val valueBytes = Bytes.toBytes("value")

  def getEdgeList(sourceId: Long) : List[Edge] = {
    //TODO
    List.empty[Edge]
  }

  def parse[V](record: Result, toValue: Array[Byte] => V) : Edge[V] = {
    val rowkey = record.getRow()
    val sourceId = Bytes.toLong(rowkey, 0, 8)
    val targetId = Bytes.toLong(rowkey, 8, 8)
    val value = toValue(record.getValue(cfBytes, valueBytes))
    new Edge(sourceId, targetId, value)
  }

  def save(edge: Edge) = {
    //TODO
    //Save to HBase
  }
}
