lazy val root = (project in file(".")).settings(
  name := "learnSbt",

  version := "0.1",

  scalaVersion := "2.12.4"

)

def betterFileVersion = "3.4.0"

libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "com.typesafe.akka" %% "akka-actor" % "2.5.9",
  "org.json" % "json" % "20171018",
  "com.google.inject" % "guice" % "4.1.0",
  "com.typesafe.akka" %% "akka-cluster" % "2.5.11",
  "com.typesafe.akka" %% "akka-cluster-tools" % "2.5.11",
  "com.github.pathikrit" %% "better-files" % betterFileVersion,
  "org.reflections" % "reflections" % "0.9.10",
  "com.storm-enroute" %% "scalameter" % "0.8.2",
  "org.apache.poi" % "poi-ooxml" % "3.9",

)
