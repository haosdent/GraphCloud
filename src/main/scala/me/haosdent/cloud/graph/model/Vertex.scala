package me.haosdent.cloud.graph.model

class Vertex[V, C](val id: Long, val in: Int, var curIn: Int, val out: Int, var value: V, var collect: C, var iter: Int) {
}
