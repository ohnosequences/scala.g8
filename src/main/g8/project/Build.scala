import sbt._
import Keys._

// sbt-release plugin
import sbtrelease.ReleasePlugin._
import sbtrelease._
import ReleaseStateTransformations._

object $name$Build extends Build {

  lazy val $name$ = Project(
    id = "$name$",
    base = file("."),
    settings = Defaults.defaultSettings ++ releaseSettings
  )

  // sample release step
  val checkOrganization = ReleaseStep(action = st => {
    // extract the build state
    val extracted = Project.extract(st)
    // retrieve the value of the organization SettingKey
    val org = extracted.get(Keys.organization)

    if (org.startsWith("era7")
      sys.error("buuuh!")
    else
      sys.error("yeah")

    st
  })


}