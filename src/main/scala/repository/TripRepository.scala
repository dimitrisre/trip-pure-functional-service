package repository

import database.models.Trip

trait TripRepository[F[_]]{
  def selectAll(page: Int, pageSize: Int, sort: String): F[Seq[Trip]]
  def select(id: Int): F[Option[Trip]]
  def insert(row: Trip): F[Int]
  def update(id: Int, row: Trip): F[Int]
  def delete(id: Int): F[Int]

  def createSchema(): F[Unit]
  def sortingFields: Set[String]
}
