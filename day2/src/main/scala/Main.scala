package main.scala

@main
def run(): Unit =
  println(pt1())
  println(pt2())

val test = """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9"""

def pt1(): Int =
  // val reader = scala.io.Source.fromString(test)
  val reader = scala.io.Source.fromFile("./day2/src/main/resources/input.txt")
  reader.getLines().map(toIntList(_)).filter(isLineSafe(_)).size

def pt2(): Int =
  // val reader = scala.io.Source.fromString(test)
  val reader = scala.io.Source.fromFile("./day2/src/main/resources/input.txt")
  reader.getLines().map(toIntList(_)).filter(isLineSafeExtended(_)).size

def checkOrder(list: List[Int], f: (Int, Int) => Boolean): Boolean =
  list.zip(list.sortWith(f)).filter(el => el._1 == el._2).size == list.size

def gt = (x: Int, y: Int) => (x > y)
def lt = (x: Int, y: Int) => (x < y)
def allTrue = (x: Boolean, y: Boolean) => (x && y)

def toIntList(s: String): List[Int] =
  s.split(" ").map(_.toInt).toList

def isSafeLevels(list: List[Int]): Boolean =
  val abs = (list(0) - list(1)).abs
  abs >= 1 && abs <= 3

def isLineSafe(intList: List[Int]): Boolean =
  val allInc = checkOrder(intList, lt)
  val allDec = checkOrder(intList, gt)
  val safeLevels = intList.sliding(2).map(isSafeLevels(_)).reduce(allTrue)
  (allDec || allInc) && safeLevels

def isLineSafeExtended(list: List[Int]): Boolean =
  list.zipWithIndex.map {
    case (_, index) =>
      val sl = list.zipWithIndex.filterNot(_._2 == index).map(_._1)
      isLineSafe(sl)
  }.filter(_ == true).size > 0