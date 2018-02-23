import scala.concurrent.Future

case class LocalResponse(statusCode: Int)

case class RemoteResponse(message: String)

sealed trait Computation {
  type Response
  val work: Future[Response]
}

case class LocalComputation(work: Future[LocalResponse]) extends Computation {
  override type Response = LocalResponse
}

case class RemoteComputation(work: Future[RemoteResponse]) extends Computation {
  override type Response = RemoteResponse
}