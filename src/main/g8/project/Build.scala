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
  val uploadArtifacts = ReleaseStep(action = st => {
    // extract the build state
    val extracted = Project.extract(st)
    // retrieve the value of the organization SettingKey
    val org = extracted.get(Keys.organization)

    val current = extracted.get(version in ThisBuild)

    if (current.endsWith("-SNAPSHOT")) {

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
      cmd (
            "echo",
            "a normal release"
          )

      cmd (
            "s3cmd", "sync", "-r", "--no-delete-removed", "--disable-multipart",
            "artifacts/releases.era7.com/",
            "s3://releases.era7.com/"
          )

    st
  })


}