case class Breakfast(egg: Int, milk: Int)

sealed trait GoToWork[A]

case object GetUp extends GoToWork[Unit]

case object Wash extends GoToWork[Unit]

case object MakeBreakfast extends GoToWork[Breakfast]

case class Eat(breakfast: Breakfast) extends GoToWork[Unit]

case object GoOut extends GoToWork[Unit]

//trait DoFree[F[_], A] {
//  def unit(a: A): DoFree[F, A] = Return(a)
//}

trait DoFree[F[_], A] {
  def unit(a: A): DoFree[F, A] = Return(a)

  def flatMap[B](k: A => DoFree[F, B]): DoFree[F, B] = this match {
    case Return(a)           =>
      k(a)
    case Bind(command, cont) =>
      Bind(command, cont andThen (_ flatMap k))
  }

  def map[B](f: A => B): DoFree[F, B] =
    flatMap(f andThen (Return(_)))
}

final case class Return[F[_], A](a: A) extends DoFree[F, A]

final case class Bind[F[_], R, A](command: F[R], cont: R => DoFree[F, A]) extends DoFree[F, A]


Bind(GetUp, (_: Unit) =>
  Bind(Wash, (_: Unit) =>
    Bind(MakeBreakfast, (breakfast: Breakfast) =>
      Bind(Eat(breakfast), (_: Unit) =>
        Bind(GoOut, (_: Unit) =>
          Return(())))))): DoFree[GoToWork, Unit]