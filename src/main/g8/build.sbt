
name := "$name$"

organization := "$org$"

version := "$version$"

scalaVersion := "$scala_version$"

// crossScalaVersions := Seq("2.10.0.RC1", "2.10.0.RC2")

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  if (v.trim.endsWith("SNAPSHOT"))
    Some(Resolver.file("local-snapshots", file("~/.era7/snapshots.era7.com")))
  else
    Some(Resolver.file("local-releases", file("~/.era7/releases.era7.com")))
}

resolvers ++= Seq (
                    "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases",
                    "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases",
                    "Sonatype Public"   at "https://oss.sonatype.org/content/groups/public",
                    "Era7 Releases"     at "http://releases.era7.com.s3.amazonaws.com",
                    "Era7 Snapshots"    at "http://snapshots.era7.com.s3.amazonaws.com"
                  )

libraryDependencies ++= Seq (
                              "com.chuusai" % "shapeless" % "1.2.3" cross CrossVersion.full
                            )

// sbt-release
releaseSettings