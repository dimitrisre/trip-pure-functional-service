package routes

import cats.implicits._
import cats.effect.Sync
import database.models.{CommandResult, Trip}
import org.http4s.HttpRoutes
import service.TripService
import org.http4s.dsl.Http4sDsl
import routes.errorhandlers.Errors.UserError
import routes.errorhandlers.HttpErrorHandler
import utils.json.CirceJsonCodecs

class CommandRoutes[F[_]: Sync](service: TripService[F])
                               (implicit errorHandler: HttpErrorHandler[F, UserError])
  extends Http4sDsl[F] with CirceJsonCodecs{

  val routes = HttpRoutes.of[F]{
    case req @ POST -> Root =>
      for{
        trip <- req.as[Trip]
        i    <- service.insert(trip)
        resp <- Ok(CommandResult(i))
      } yield resp

    case req @ PUT -> Root / IntVar(id) =>
      for {
        trip <- req.as[Trip]
        i    <- service.update(id, trip)
        resp <- Ok(CommandResult(i))
      } yield resp

    case DELETE -> Root / IntVar(id) =>
      for {
        i    <- service.delete(id)
        resp <- Ok(CommandResult(i))
      } yield resp
  }
}
