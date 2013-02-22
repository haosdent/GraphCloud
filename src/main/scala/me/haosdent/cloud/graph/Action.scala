package me.haosdent.cloud.graph

object Action extends Enumeration {
  type Action = Value
  val Uninit = Value("uninit")
  val Start = Value("start")
  val Stop = Value("stop")
  val End = Value("end")
  val Ask = Value("ask")
}

