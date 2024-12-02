import mill._
import mill.scalalib._

object day1 extends ScalaModule {

  def scalaVersion = "3.6.1"

  object test extends ScalaTests with TestModule.Munit {
    def ivyDeps = Agg(
      ivy"org.scalameta::munit::1.0.1"
    )
  }
}

object day2 extends ScalaModule {

  def scalaVersion = "3.6.1"

  object test extends ScalaTests with TestModule.Munit {
    def ivyDeps = Agg(
      ivy"org.scalameta::munit::1.0.1"
    )
  }
}