package routes

import cats.implicits._
import cats.effect.Sync
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.dsl.impl.OptionalQueryParamDecoderMatcher
import routes.errorhandlers.Errors.UserError
import routes.errorhandlers.HttpErrorHandler
import service.TripService
import utils.json.CirceJsonCodecs

object OptSort extends OptionalQueryParamDecoderMatcher[String]("sort")
object OptPage extends OptionalQueryParamDecoderMatcher[Int]("page")
object OptPageSize extends OptionalQueryParamDecoderMatcher[Int]("pageSize")

class QueryRoutes[F[_]: Sync] (service: TripService[F])
                              (implicit errorHandler: HttpErrorHandler[F, UserError])
  extends Http4sDsl[F]
    with CirceJsonCodecs{

  val routes = HttpRoutes.of[F]{
    case GET -> Root / IntVar(id) =>
      val trip = service.select(id)
      trip.flatMap(_.fold(NotFound())(Ok(_)))

    case GET -> Root :? OptSort(sort) +& OptPage(page) +& OptPageSize(pageSize) =>
      service
        .selectAll(page, pageSize, sort)
        .flatMap(Ok(_))
  }

}
