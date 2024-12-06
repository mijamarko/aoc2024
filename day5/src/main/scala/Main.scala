package main.scala

import common.getInput

@main def run() =
  val input = getInput("day5")
//   val input = testInput.split("\n").toList
  val cmp = input
    .takeWhile(_ != "")
    .map(e => (e.substring(0, e.indexOf("|")), e.substring(e.indexOf("|") + 1)))
    .groupBy(_._1)
    .map(_._2)
    .map { el =>
      val k = el(0)._1
      val m = el.map(_._2).toSet
      (k, m)
    }
    .toMap
  val ord = input.dropWhile(_ != "").drop(1).map(_.split(",").toList)
//   println(sumCorrect(cmp, ord))
  println(sumIncorrect(cmp, ord))

end run

def sumCorrect(cmp: Map[String, Set[String]], ord: List[List[String]]): Int =
  ord.map { el =>
    val res = el.map { i =>
      el.slice(el.indexOf(i) + 1, el.length)
        .map(ri =>
          cmp.get(ri) match
              case Some(set) => !set.contains(i)
              case None      => true
        )
        .forall(el => el == true)
    }
    if res.forall(_ == true) then el(el.length / 2).toInt else 0
  }.sum
end sumCorrect

def sumIncorrect(cmp: Map[String, Set[String]], ord: List[List[String]]): Int =
  ord.map { el =>
    val res = el.map { i =>
      el.slice(el.indexOf(i) + 1, el.length)
        .map { ri =>
          cmp.get(ri) match
              case Some(set) => set.contains(i)
              case None      => true
        }
        .contains(true)
    }
    if res.contains(true)
    then
      val newList = el.sortWith { (x, y) =>
        cmp.get(x) match
            case Some(value) => value.contains(y)
            case None        => false
      }
      println(el)
      println("-"*20)
      println(newList)
      println()
      if el != newList then 
      newList(newList.length / 2).toInt
      else 0
    else 0
  }.sum

val testInput = """47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47"""
