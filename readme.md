# Project template: _general Scala project_

This is a general template for Era7 Scala projects. It helps you to get the initial project structure.

First of all you will need [sbt v1.0 or newer](http://www.scala-sbt.org/download.html). To apply this template, go to the directory where you keep projects and run

```shell
sbt new era7bio/scala.g8
```

It will ask you for the project name and organization and create a new folder.

To proceed with the Git/Github setup refer to our general docs for [New projects](https://github.com/era7bio/docs/wiki/New-projects).


## Project structure

This template uses [standard sbt project structure](http://www.scala-sbt.org/release/docs/Directories.html). The main differences from the [official scala-seed template](https://github.com/scala/scala-seed.g8) are

* we use our [ohnosequences/nice-sbt-settings](https://github.com/ohnosequences/nice-sbt-settings) plugin which includes several other useful plugins and settings to reduce project definition boilerplate and standardize the release process
* `build.sbt` is pre-populated with some necessary settings and some optional ones that are commented out in case you'll need them
* default license for our software projects is [AGPLv3](https://tldrlegal.com/license/gnu-affero-general-public-license-v3-%28agpl-3.0%29)
* we add `docs/` directory for any handwritten documentation
* we add `notes/` directory for release notes: keep `notes/changelog.md` up to date during development, every time you make a release, it will be used for release notes
