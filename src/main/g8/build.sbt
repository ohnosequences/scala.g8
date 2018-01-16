name          := "$name$"
organization  := "$org$"
description   := "$name$ project"

bucketSuffix  := "era7.com"

// dependencies
////////////////////////////////////////////////////////////////////////////////
libraryDependencies ++= mainDependencies ++ testDependencies

val mainDependencies = 
  Seq()

val testDependencies = 
  Seq(
    "org.scalatest" %% "scalatest" % "$scalatest_version$"
  )
  .map(_ % Test)

// test settings
////////////////////////////////////////////////////////////////////////////////
// shows time for each test
testOptions in Test += Tests.Argument("-oD")
// disables parallel execution
parallelExecution in Test := false

// For resolving dependencies version conflicts:
// dependencyOverrides ++= Set()

// Uncomment if you need to deploy this project as a Statika bundle:
// generateStatikaMetadataIn(Compile)

// Uncomment if you have release-only tests using the assembled fat-jar:
// fullClasspath in assembly := (fullClasspath in Test).value

// Uncomment for Java projects:
// enablePlugin(JavaOnlySettings)
