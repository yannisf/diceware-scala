import sbt.url

val AppScalaVersion = "2.12.9"

ThisBuild / scalaVersion := AppScalaVersion

ThisBuild / organization := "eu.frlab"
ThisBuild / organizationName := "FRLab"
ThisBuild / description := "Diceware passwords"
ThisBuild / licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
ThisBuild / homepage := Some(url("https://frlab.eu/diceware"))
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/yannisf/scala-diceware"),
    "scm:https://github.com/yannisf/scala-diceware.git"
  )
)

ThisBuild / developers := List(
  Developer(
    id = "yannisf",
    name = "Yannis",
    email = "yannis@ouranos",
    url = url("https://frlab.eu")
  )
)

lazy val assemblySettings = Seq(
  assembly / mainClass := Some("eu.frlab.diceware.DicewareServerMain"),
  assembly / assemblyJarName := "diceware-server.jar",
  assembly / assemblyOption := (assemblyOption in assembly).value.copy(includeScala = true),
  assembly / assemblyMergeStrategy := {
    case PathList("META-INF", xs@_*) => MergeStrategy.discard
    case x => MergeStrategy.first
  }
)

lazy val dockerSettings = Seq(
  docker / dockerfile := {
    val artifact = assembly.value
    new Dockerfile {
      from("openjdk:8-alpine")
      workDir("/root")
      copy(artifact, destination = "/root/")
      expose(8888)
      entryPointRaw("java -jar diceware-server.jar")
    }
  },
  docker / imageNames := Seq(ImageName("frlab/diceware:0.1")),
  docker / buildOptions := BuildOptions(cache = false))

lazy val root = project.in(file("."))
  .aggregate(services, server)
  .settings(jacocoAggregateReportSettings := JacocoReportSettings(formats = Seq(JacocoReportFormats.XML)))
  .settings(name := "diceware")

lazy val services = project.in(file("services"))
  .settings(jacocoReportSettings := JacocoReportSettings(formats = Seq(JacocoReportFormats.XML)))
  .settings(libraryDependencies += Dependencies.ScalaTest)
  .settings(libraryDependencies ++= Dependencies.Logging)
  .settings(libraryDependencies += "javax.inject" % "javax.inject" % "1")

lazy val server = project.in(file("server"))
  .dependsOn(services)
  .settings(jacocoReportSettings := JacocoReportSettings(formats = Seq(JacocoReportFormats.XML)))
  .settings(libraryDependencies ++= Dependencies.Twitter)
  .settings(libraryDependencies ++= Dependencies.Finagle)
  .settings(libraryDependencies += Dependencies.ScalaTest)
  .settings(libraryDependencies ++= Dependencies.Logging)
  .settings(libraryDependencies += Dependencies.Pureconfig)
  .settings(assemblySettings)
