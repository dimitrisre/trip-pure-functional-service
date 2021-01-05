package service
import database.models.{Trip, Trips}
import repository.TripRepository

class TripService[F[_]](repository: TripRepository[F]) extends TripAlg[F] {
  override def selectAll(page: Option[Int], pageSize: Option[Int], sort: Option[String]): F[Trips] = ???

  override def select(id: Int): F[Option[Int]] = ???

  override def insert(trip: Trip): F[Int] = ???

  override def update(id: Int, trip: Trip): F[Int] = ???

  override def delete(id: Int): F[Int] = ???
}
