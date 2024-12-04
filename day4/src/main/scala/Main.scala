package main.scala

import common.getInput

import scala.io.Source
import scala.util.{ Failure, Success }

@main def run(): Unit =
  val str = getInput("day4")
  // val input =
  //   """MMMSXXMASM
  //     |MSAMXMSMSA
  //     |AMXSXMAAMM
  //     |MSAMASMSMX
  //     |XMASAMXAMM
  //     |XXAMMXXAMA
  //     |SMSMSASXSS
  //     |SAXAMASAAA
  //     |MAMMMXMMMM
  //     |MXMXAXMASX""".stripMargin
  // val str = Source.fromString(input).getLines().toList
  println(getPossibleCombinations(str).length)
  println(filterMas(str))

def getPossibleCombinations(in: List[String]): List[String] =
  val response = for {
    i <- 0 to in.head.length
    j <- 0 to in.length
    ltr =
      try Success(List(in(j)(i), in(j)(i + 1), in(j)(i + 2), in(j)(i + 3)).mkString)
      catch case e: Throwable => Failure(e)
    rtl =
      try Success(List(in(j)(i), in(j)(i - 1), in(j)(i - 2), in(j)(i - 3)).mkString)
      catch case e => Failure(e)
    up =
      try Success(List(in(j)(i), in(j - 1)(i), in(j - 2)(i), in(j - 3)(i)).mkString)
      catch case e => Failure(e)
    down =
      try Success(List(in(j)(i), in(j + 1)(i), in(j + 2)(i), in(j + 3)(i)).mkString)
      catch case e => Failure(e)
    topr =
      try Success(List(in(j)(i), in(j - 1)(i + 1), in(j - 2)(i + 2), in(j - 3)(i + 3)).mkString)
      catch case e => Failure(e)
    topl =
      try Success(List(in(j)(i), in(j - 1)(i - 1), in(j - 2)(i - 2), in(j - 3)(i - 3)).mkString)
      catch case e => Failure(e)
    botr =
      try Success(List(in(j)(i), in(j + 1)(i + 1), in(j + 2)(i + 2), in(j + 3)(i + 3)).mkString)
      catch case e => Failure(e)
    botl =
      try Success(List(in(j)(i), in(j + 1)(i - 1), in(j + 2)(i - 2), in(j + 3)(i - 3)).mkString)
      catch case e => Failure(e)
    result = List(ltr, rtl, up, down, topr, topl, botr, botl).filter(_.isSuccess).map(_.get).filter(_ == "XMAS")
  } yield result
  response.toList.flatten

def filterMas(in: List[String]): Int =
  val response = for {
    i <- 0 to in.head.length
    j <- 0 to in.length
    res =
      try {
        val fr = List(in(j)(i), in(j + 1)(i + 1), in(j + 2)(i + 2)).mkString
        val bl = List(in(j)(i + 2), in(j + 1)(i + 1), in(j + 2)(i)).mkString
        if (fr == "MAS" || fr.reverse == "MAS") && (bl == "MAS" || bl.reverse == "MAS") then Success(true)
        else Success(false)
      } catch case e => Failure(e)
  } yield res
  response.filter(_.isSuccess).map(_.get).filter(_ == true).length
