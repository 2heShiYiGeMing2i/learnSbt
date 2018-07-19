lazy val root = (project in file(".")).settings(
  name := "learnSbt",
  version := "0.1",
  scalaVersion := "2.12.4"
)

def betterFileVersion = "3.4.0"

libraryDependencies ++= Seq(
  "org.scalatest"        %% "scalatest"          % "3.0.4" % "test",
  "com.typesafe.akka"    %% "akka-actor"         % "2.5.9",
  "org.json"             % "json"                % "20171018",
  "com.google.inject"    % "guice"               % "4.1.0",
  "com.typesafe.akka"    %% "akka-cluster"       % "2.5.11",
  "com.typesafe.akka"    %% "akka-cluster-tools" % "2.5.11",
  "com.github.pathikrit" %% "better-files"       % betterFileVersion,
  "org.reflections"      % "reflections"         % "0.9.10",
  "com.storm-enroute"    %% "scalameter"         % "0.8.2",
  "org.apache.poi"       % "poi-ooxml"           % "3.9",
  "javax.validation"     % "validation-api"      % "2.0.1.Final",
  "org.hibernate"        % "hibernate-validator" % "6.0.10.Final",
  "javax.el"             % "javax.el-api"        % "3.0.0",
  "org.glassfish.web"    % "javax.el"            % "2.2.6",
  "com.twitter"          %% "chill"              % "0.9.2",
  //  "com.github.romix.akka" %% "akka-kryo-serialization" % "0.5.2",
  //  "com.esotericsoftware"  % "kryo"                     % "5.0.0-RC1", // 不支持caseclass 序列化
)

val gen = taskKey[Unit]("就是 描述 咋滴吧")

gen := {
  println(" jiu  shi  ce  shi 任性的测试")
}
