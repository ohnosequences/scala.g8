import sbtrelease._
import ReleaseStateTransformations._
import ReleasePlugin._
import ReleaseKeys._


name := "$name$"

description := "$name$ project"

homepage := "https://github.com/$org$/$name$"

organization := "$org$"

organizationHomepage := "http://$org$.com"

licenses := "AGPLv3" -> "http://www.gnu.org/licenses/agpl-3.0.txt"


scalaVersion := "$scala_version$"


// for publishing you need to set `s3credentials`
publishTo <<= (isSnapshot, s3credentials) { 
                (snapshot,   credentials) => 
  val prefix = if (snapshot) "snapshots" else "releases"
  credentials map s3resolver("Era7 "+prefix+" S3 bucket", "s3://"+prefix+".era7.com")
}


resolvers ++= Seq ( 
    Resolver.typesafeRepo("releases")
  , Resolver.sonatypeRepo("releases")
  , Resolver.sonatypeRepo("snapshots")
  , "Era7 Releases"  at "http://releases.era7.com.s3.amazonaws.com"
  , "Era7 Snapshots" at "http://snapshots.era7.com.s3.amazonaws.com"
  )


scalacOptions ++= Seq(
    "-feature"
  , "-language:higherKinds"
  , "-language:implicitConversions"
  , "-language:postfixOps"
  , "-deprecation"
  , "-unchecked"
  )


// sbt-release settings
releaseSettings

releaseProcess <<= thisProjectRef apply { ref =>
  Seq[ReleaseStep](
    checkSnapshotDependencies
  , inquireVersions
  , runTest
  , setReleaseVersion
  , commitReleaseVersion
  , tagRelease
  , publishArtifacts
  , setNextVersion
  , commitNextVersion
  , pushChanges
  )
}
