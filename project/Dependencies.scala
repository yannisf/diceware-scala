import sbt._

object Dependencies {

  final val version = new {
    final val TwitterLib = "20.3.0"
    final val LogbackVersion = "1.2.3"
    final val Slf4jVersion = "1.7.30"
    final val ScalaTest = "3.1.1"
  }

  private final val groups = new {
    final val Twitter = "com.twitter"
    final val SLF4J = "org.slf4j"
  }

  lazy val Twitter = Seq(
    groups.Twitter %% "finatra-http" % version.TwitterLib,
    groups.Twitter %% "finatra-http" % version.TwitterLib % Test classifier "tests",
    groups.Twitter %% "inject-core" % version.TwitterLib,
    groups.Twitter %% "inject-core" % version.TwitterLib % Test classifier "tests",
    groups.Twitter %% "inject-app" % version.TwitterLib,
    groups.Twitter %% "inject-app" % version.TwitterLib % Test classifier "tests",
    groups.Twitter %% "inject-server" % version.TwitterLib,
    groups.Twitter %% "inject-server" % version.TwitterLib % Test classifier "tests",
    groups.Twitter %% "inject-modules" % version.TwitterLib,
    groups.Twitter %% "inject-modules" % version.TwitterLib % Test classifier "tests",
    //    "com.twitter" %% "twitter-server-slf4j-jdk14" % version.TwitterLib
  )

  lazy val Finagle = Seq(
    groups.Twitter %% "finagle-core" % version.TwitterLib exclude(groups.Twitter, "libthrift"),
    groups.Twitter %% "finagle-http" % version.TwitterLib exclude(groups.Twitter, "libthrift"))

  lazy val Logging = Seq(
    "ch.qos.logback" % "logback-classic" % version.LogbackVersion,
    groups.SLF4J % "jcl-over-slf4j" % version.Slf4jVersion,
    groups.SLF4J % "log4j-over-slf4j" % version.Slf4jVersion,
    groups.SLF4J % "jul-to-slf4j" % version.Slf4jVersion)

  lazy val Pureconfig = "com.github.pureconfig" %% "pureconfig" % "0.12.3"

  lazy val ScalaTest = "org.scalatest" %% "scalatest" % version.ScalaTest % "test"

}
