package me.haosdent.cloud.graph.model

class Vertex(val id: Long, val in: Int, var curIn: Int, val out: Int, var value: Object, var result: Object, var iter: Int) {

  def initAsSource() = {
    //TODO
    //1.update value(value = result)
    //2.reset result(result = 0)
    //3.save
    save()
  }

  def finishAsTarget() = {
    //TODO
    curIn = curIn + 1
    if (curIn == in) {
      iter = iter + 1
      curIn = 0
      //FIXME
      val limit = 0
      if (iter <= limit) {
        val task = new Task(id)
        task.save()
      }
    }
    save()
  }

  def save() = {
    //TODO
    //save to HBase
  }
}
