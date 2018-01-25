val booleans = Seq(true, false)

for (elem <- booleans) {
  case true  => System.err.println("got head")
  case false => System.err.println("got tails")
}