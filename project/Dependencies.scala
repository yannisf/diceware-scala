import sbt._

object Dependencies {

  final val version = new {
    final val LogbackVersion = "1.2.3"
    final val Slf4jVersion = "1.7.25"
    final val ScalaTest = "3.0.5"
    final val TwitterLib = "19.4.0"
    final val Jackson = "2.9.8"
  }

  lazy val Twitter = Seq(
    "com.twitter" %% "finatra-http" % version.TwitterLib,
    "com.twitter" %% "finatra-http" % version.TwitterLib % Test classifier "tests",
    "com.twitter" %% "inject-core" % version.TwitterLib,
    "com.twitter" %% "inject-core" % version.TwitterLib % Test classifier "tests",
    "com.twitter" %% "inject-app" % version.TwitterLib,
    "com.twitter" %% "inject-app" % version.TwitterLib % Test classifier "tests",
    "com.twitter" %% "inject-server" % version.TwitterLib,
    "com.twitter" %% "inject-server" % version.TwitterLib % Test classifier "tests",
    "com.twitter" %% "inject-modules" % version.TwitterLib,
    "com.twitter" %% "inject-modules" % version.TwitterLib % Test classifier "tests",
    //    "com.twitter" %% "twitter-server-slf4j-jdk14" % version.TwitterLib
  )

  lazy val Finagle = Seq(
    "com.twitter" %% "finagle-core" % version.TwitterLib exclude("com.twitter", "libthrift"),
    "com.twitter" %% "finagle-http" % version.TwitterLib exclude("com.twitter", "libthrift"))

  lazy val Logging = Seq(
    "ch.qos.logback" % "logback-classic" % version.LogbackVersion,
    "org.slf4j" % "jcl-over-slf4j" % version.Slf4jVersion,
    "org.slf4j" % "log4j-over-slf4j" % version.Slf4jVersion,
    "org.slf4j" % "jul-to-slf4j" % version.Slf4jVersion)

  lazy val ScalaTest = "org.scalatest" %% "scalatest" % version.ScalaTest % "test"

}
