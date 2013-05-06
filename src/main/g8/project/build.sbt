// S3 bucket resolver dependency
resolvers ++= Seq ( DefaultMavenRepository
                  , "nexus CPD" at "http://nexus.cestpasdur.com/nexus/content/repositories/everything/"
                  )

libraryDependencies += "org.springframework.aws" % "spring-aws-ivy" % "1.0.3"

// sbt-release plugin
addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.7")
