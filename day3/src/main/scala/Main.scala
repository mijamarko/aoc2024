package main.scala

import scala.io.Source
import common.getInput

@main
def run(): Unit =
  val input = getInput("day3")
  println(pt1(input))
  println(pt2(input))

val test  = """xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))""" //161
val test2 = """xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"""

def mulStr(list: List[String]): Int =
  list(0).trim().toInt * list(1).trim().toInt

def trimMul(str: String): String =
  str.substring(4, str.length() - 1)

def pt1(src: String): Int =
  """mul\(\d+,\d+\)""".r
    .findAllIn(src)
    .map(_ match {
        case s"mul($x,$y)" => x.toInt * y.toInt
    })
    .sum

case class State(sum: Int = 0, doing: Boolean = true)

def pt2(src: String): Int =
  """mul\(\d+,\d+\)|do\(\)|don't\(\)""".r
    .findAllIn(src)
    .foldLeft(State()) {
      case (State(soFar, true), s"mul($x,$y)") => State(sum = soFar + x.toInt * y.toInt)
      case (s, "don't()")                      => s.copy(doing = false)
      case (s, "do()")                         => s.copy(doing = true)
      case (s, _)                              => s
    }
    .sum
