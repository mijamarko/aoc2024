package main.scala

@main
def run(): Unit = {
  val reader = scala.io.Source.fromFile("./day1/src/main/resources/input.txt")
  val (odds, evens) = reader
    .getLines()
    .flatMap(l => l.split("\\s+"))
    .zipWithIndex
    .partition(_._2 % 2 != 0)
  val oddList  = odds.map(_._1.toInt).toList.sortWith((x, y) => x < y)
  val evenList = evens.map(_._1.toInt).toList.sortWith((x, y) => x < y)
  val respt1   = evenList.zip(oddList).map(x => (x._1 - x._2).abs).sum
  val respt2   = evenList.map(el => oddList.count(_ == el) * el).sum
  reader.close()
}
