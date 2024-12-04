package common
import scala.io.Source

def getInput(srcDir: String, input: String = "input.txt"): String =
  Source.fromFile(s"./$srcDir/src/main/resources/$input").getLines.toList.mkString
