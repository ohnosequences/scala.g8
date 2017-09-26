name          := "$name$"
organization  := "$org$"
description   := "$name$ project"

bucketSuffix  := "era7.com"

libraryDependencies ++= Seq()

// For resolving dependencies version conflicts:
// dependencyOverrides ++= Set()

// Uncomment if you need to deploy this project as a Statika bundle:
// generateStatikaMetadataIn(Compile)

// Uncomment if you have release-only tests using the assembled fat-jar:
// fullClasspath in assembly := (fullClasspath in Test).value

// Uncomment for Java projects:
// enablePlugin(JavaOnlySettings)
