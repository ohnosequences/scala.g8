resolvers ++= Seq (
  Resolver.url("Era7 Ivy Releases", url("http://releases.era7.com.s3.amazonaws.com"))(
    Patterns("[organisation]/[module]/[revision]/[type]s/[artifact](-[classifier]).[ext]"))
, "nexus CPD" at "http://nexus.cestpasdur.com/nexus/content/repositories/everything/"
, DefaultMavenRepository
)

addSbtPlugin("ohnosequences" % "sbt-s3-resolver" % "0.3.0")

addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.7")

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.2.4")
