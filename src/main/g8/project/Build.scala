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
    settings = Defaults.defaultSettings ++ releaseSettings ++ Seq(

        releaseProcess <<= thisProjectRef apply { ref =>
          Seq[ReleaseStep](
            checkSnapshotDependencies,              // : ReleaseStep
            inquireVersions,                        // : ReleaseStep
            runTest,                                // : ReleaseStep
            setReleaseVersion,                      // : ReleaseStep
            commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
            tagRelease,                             // : ReleaseStep
            publishArtifacts,                       // : ReleaseStep, checks whether `publishTo` is properly set up
            setNextVersion,                         // : ReleaseStep
            commitNextVersion,                      // : ReleaseStep
            pushChanges                             // : ReleaseStep, also checks that an upstream branch is properly configured
          )
        }
      )
  )

val credentials = {
  // TODO: make credentials findong more flexible
  val path = Path.userHome / ".ec2" / "PrivateS3Credentials.properties"
  if (!path.exists)
    Left("Credentials file " + path + " does not exist")
  else {
    val p = new java.util.Properties
    p.load(new java.io.FileInputStream(path))
    Right(p.getProperty("accessKey"),
          p.getProperty("secretKey"))
  }
}

def s3resolver(
    isSnapshot: Boolean = true
  , isPrivate:  Boolean = true
  , publisher:  Boolean = false
  ) = {
  val s3r = new org.springframework.aws.ivy.S3Resolver()
  s3r.setName("S3 "+
    (if(isSnapshot) "snapshots " else "releases ")+
    (if(isPrivate) "private " else "public ")+
    "bucket "+ 
    (if(publisher) "publisher" else "resolver"))

  credentials match {
    case Right((user, pass)) => {
      s3r.setAccessKey(user)
      s3r.setSecretKey(pass)
    }
    case Left(errors) => credentials.left.map(println)
  }

  val pattern = "s3://"+
                (if(isPrivate) "private." else "")+
                (if(isSnapshot) "snapshots" else "releases")+
                ".statika.ohnosequences.com/[organisation]/[module]/[revision]/[type]s/[artifact].[ext]"
  s3r.addArtifactPattern(pattern)
  s3r.addIvyPattern(pattern)

  new sbt.RawRepository(s3r)
}

val s3resolvers = Seq(
  s3resolver(isSnapshot = true,  isPrivate = true)
, s3resolver(isSnapshot = true,  isPrivate = false)
, s3resolver(isSnapshot = false, isPrivate = true)
, s3resolver(isSnapshot = false, isPrivate = false)
)

}
