package main.scala

@main
def run(): Unit =
  println(pt1())

def pt1(): Int =
  val test = """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9"""
  // val reader = scala.io.Source.fromString(test)
  val reader = scala.io.Source.fromFile("./day2/src/main/resources/input.txt")
  reader.getLines().filter(l => {
    val intList = l.split(" ").map(_.toInt).toList
    val allInc = intList.zip(intList.sortWith((x, y) => x > y)).filter(x => x._1 == x._2).size == intList.size
    val allDec = intList.zip(intList.sortWith((x, y) => x < y)).filter(x => x._1 == x._2).size == intList.size
    val safeLevels = intList.sliding(2).map(x => {
      val abs = (x(0) - x(1)).abs
      abs >= 1 && abs <= 3
    }).reduce((x, y) => x && y)
    (allDec || allInc) && safeLevels
  }).size