resolvers += Resolver.url("Pollfish SBT repo", url("http://artifactory.int.pollfish.com:8081/artifactory/sbt-repos/"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.pollfish" % "pf-sbt-bigdata" % "1.3.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.15")
addSbtPlugin("se.marcuslonnberg" % "sbt-docker" % "1.5.0")
addSbtPlugin("com.pollfish" % "pf-sbt-parent" % "2.2.1")
