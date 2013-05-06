
import sbtrelease._
import ReleaseStateTransformations._

import $name$Build._

name := "$name$"

organization := "$org$"

version := "$version$"

scalaVersion := "$scala_version$"

// crossScalaVersions := Seq("2.9.1", "2.9.2", "2.10.0")

publishMavenStyle := false

publishTo <<= (version) { (v: String) =>
  Some(s3resolver(isSnapshot = v.trim.endsWith("SNAPSHOT"), isPrivate = true, publisher = true))
}

resolvers ++= Seq (
                    "Typesafe Releases"   at "http://repo.typesafe.com/typesafe/releases"
                  , "Sonatype Releases"   at "https://oss.sonatype.org/content/repositories/releases"
                  , "Sonatype Snapshots"  at "https://oss.sonatype.org/content/repositories/snapshots"
                  , "Era7 Releases"       at "http://releases.era7.com.s3.amazonaws.com"
                  , "Era7 Snapshots"      at "http://snapshots.era7.com.s3.amazonaws.com"
                  )

resolvers ++= s3resolvers

libraryDependencies ++= Seq (
                              "com.chuusai" %% "shapeless" % "1.2.3"
                            )

scalacOptions ++= Seq("-feature"
                    , "-language:higherKinds"
                    , "-language:implicitConversions"
                    , "-deprecation"
                    , "-unchecked"
                    )
