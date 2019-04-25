import com.pollfish.sbt.BaseDependencies
import sbt._

object Dependencies extends BaseDependencies {

  object v {
    final val LogbackVersion = "1.2.3"
    final val Slf4jVersion = "1.7.25"
    final val TypesafeConfig = "1.3.2"
  }

  lazy val Twitter = Seq(
    "com.twitter" %% "finatra-http" % version.TwitterLib,
    "com.twitter" %% "finatra-http" % version.TwitterLib % Test classifier "tests",

    "com.twitter" %% "finatra-thrift" % version.TwitterLib,
    "com.twitter" %% "finatra-thrift" % version.TwitterLib % Test classifier "tests",

    "com.twitter" %% "inject-core" % version.TwitterLib,
    "com.twitter" %% "inject-core" % version.TwitterLib % Test classifier "tests",

    "com.twitter" %% "inject-app" % version.TwitterLib,
    "com.twitter" %% "inject-app" % version.TwitterLib % Test classifier "tests",

    "com.twitter" %% "inject-server" % version.TwitterLib,
    "com.twitter" %% "inject-server" % version.TwitterLib % Test classifier "tests",

    "com.twitter" %% "inject-modules" % version.TwitterLib,
    "com.twitter" %% "inject-modules" % version.TwitterLib % Test classifier "tests",

    "com.twitter" %% "inject-thrift-client" % version.TwitterLib
  )

  lazy val Logging = Seq(
    "ch.qos.logback" % "logback-classic" % v.LogbackVersion,
    "org.slf4j" % "jcl-over-slf4j" % v.Slf4jVersion,
    "org.slf4j" % "log4j-over-slf4j" % v.Slf4jVersion,
    "org.slf4j" % "jul-to-slf4j" % v.Slf4jVersion
  )

  lazy val TypesafeConfig = "com.typesafe" % "config" % v.TypesafeConfig

}
