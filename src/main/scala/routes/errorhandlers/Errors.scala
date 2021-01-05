package routes.errorhandlers

import database.models.Trip

object Errors {
  sealed trait UserError extends Exception
  case class InvalidTrip(trip: Trip, msg: String) extends UserError
  case class UnknownSortField(field: String) extends UserError
}
