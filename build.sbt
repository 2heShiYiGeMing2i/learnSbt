lazy val root = (project in file(".")).settings(
  name := "learnSbt",

  version := "0.1",

  scalaVersion := "2.12.4"

)
libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "com.typesafe.akka" %% "akka-actor" % "2.5.9",
  "org.json" % "json" % "20171018",
  "com.google.inject" % "guice" % "4.1.0"
)
