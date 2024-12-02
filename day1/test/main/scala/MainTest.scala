package code

class MySuite extends munit.FunSuite {
  test("hello") {
    assertEquals(Main.msg, "Hello World!")
  }
}

