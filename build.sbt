name := "trip-pure-functional-service"

version := "0.1"

scalaVersion := "2.13.4"

scalacOptions ++= Seq(
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps"
)

val catsVersion = "2.3.1"
val doobieVersion = "0.9.0"
val http4sVersion = "1.0-107-6676c1e"
val fs2Version = "3.0-5795280"
val circeVersion = "0.12.3"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % catsVersion,
  "org.typelevel" %% "cats-effect" % catsVersion,
  // Start with this one
  "org.tpolecat" %% "doobie-core"      % doobieVersion,
  "io.circe" %% "circe-core"    % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser"  % circeVersion,
  // And add any of these as needed
  "org.tpolecat" %% "doobie-h2"        % doobieVersion,          // H2 driver 1.4.200 + type mappings.
  "org.tpolecat" %% "doobie-hikari"    % doobieVersion,          // HikariCP transactor.
  "org.tpolecat" %% "doobie-postgres"  % doobieVersion,          // Postgres driver 42.2.12 + type mappings.
  "org.tpolecat" %% "doobie-quill"     % doobieVersion,          // Support for Quill 3.5.1
  "co.fs2" %% "fs2-core" % fs2Version,
  "org.http4s" %% "http4s-core" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.tpolecat" %% "doobie-scalatest" % doobieVersion % "test",  // ScalaTest support for typechecking statements.
  "org.tpolecat" %% "doobie-specs2"    % doobieVersion % "test" // Specs2 support for typechecking statements.
)
