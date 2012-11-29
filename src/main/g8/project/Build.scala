import sbt._
import Keys._

// sbt-release plugin
import sbtrelease.ReleasePlugin._
import sbtrelease._
import ReleaseStateTransformations._
import sbtrelease.ReleasePlugin.ReleaseKeys._

object $name$Build extends Build {

  lazy val $name$ = Project(
    id = "$name$",
    base = file("."),
    settings = Defaults.defaultSettings ++ releaseSettings
  )

  // sample release step
  val checkRelease = ReleaseStep(action = st => {
    // extract the build state
    val extracted = Project.extract(st)
    // retrieve the value of the organization SettingKey
    val org = extracted.get(Keys.organization)

    val ver = extracted.get(releaseVersion)

    if (ver.qualifier == Some("-SNAPSHOT")) {

      cmd (
            "echo",
            "a snapshot release"
          )

      cmd (
            "s3cmd", "sync", "-r", "--no-delete-removed", "--disable-multipart",
            "artifacts/snapshots.era7.com/",
            "s3://snapshots.era7.com"
          )
    } else
      sys.error("yeah")

    st
  })


}