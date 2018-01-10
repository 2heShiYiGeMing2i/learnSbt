lazy val root = (project in file(".")).settings(
  name := "learnSbt",

  version := "0.1",

  scalaVersion := "2.12.4"

)
libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "com.google.inject" % "guice" % "4.0")
