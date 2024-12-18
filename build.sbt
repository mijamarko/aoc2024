name       := "AOC2024"
sbtVersion := "1.10.6"

val commonSettings = Seq(
    scalaVersion := "3.6.1"
)

lazy val root = (project in file("."))
  .aggregate(common, day1, day2, day3, day4, day5)

lazy val common = (project in file("common")).settings(commonSettings *)
lazy val day1   = (project in file("day1")).dependsOn(common).settings(commonSettings *)
lazy val day2   = (project in file("day2")).dependsOn(common).settings(commonSettings *)
lazy val day3   = (project in file("day3")).dependsOn(common).settings(commonSettings *)
lazy val day4   = (project in file("day4")).dependsOn(common).settings(commonSettings *)
lazy val day5   = (project in file("day5")).dependsOn(common).settings(commonSettings *)