lazy val root = project
  .in(file("."))
  .settings(
    name := "everything-everywhere-all-at-once",
    organization := "pl.tomaszgigiel",
    version := "1003-SNAPSHOT",
    scalaVersion := "3.2.0",
    scalacOptions := Seq(
      "-indent",
      "-new-syntax",
      "-explain"),
    licenses := List("Apache License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "2.8.0",
      "org.typelevel" %% "cats-effect" % "3.4-389-3862cf0",
      "org.apache.commons" % "commons-collections4" % "4.4",
      "commons-io" % "commons-io" % "2.11.0",
      "org.apache.commons" % "commons-lang3" % "3.12.0",
      "commons-codec" % "commons-codec" % "1.15",
      "org.apache.commons" % "commons-compress" % "1.21",
      "org.tpolecat" %% "doobie-core" % "1.0.0-RC2",
      "org.tpolecat" %% "doobie-h2" % "1.0.0-RC2",
      "org.tpolecat" %% "doobie-hikari" % "1.0.0-RC2",
      "org.tpolecat" %% "doobie-scalatest" % "1.0.0-RC2" % Test,
      "org.freemarker" % "freemarker" % "2.3.31",
      "org.openjdk.jmh" % "jmh-core" % "1.35",
      "org.openjdk.jmh" % "jmh-generator-annprocess" % "1.35",
      "org.junit.jupiter" % "junit-jupiter" % "5.9.1" % Test,
      "org.apache.logging.log4j" % "log4j-core" % "2.19.0",
      "org.apache.logging.log4j" % "log4j-api" % "2.19.0",
      "ch.qos.logback" % "logback-classic" % "1.4.4",
      "org.projectlombok" % "lombok" % "1.18.24" % Provided,
      "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test,
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
      "org.scalatest" %% "scalatest" % "3.2.14" % Test,
      "edu.stanford.nlp" % "stanford-corenlp" % "4.5.1",
      "com.typesafe" % "config" % "1.4.2",
      "org.zeroturnaround" % "zt-zip" % "1.15",
      "com.ibm.informix" % "jdbc" % "4.50.8",
      "org.eclipse.angus" % "angus-mail" % "2.0.1",
      "com.itextpdf" % "itextpdf" % "5.5.13.3",
      "org.apache.commons" % "commons-csv" % "1.10.0"
    ),
    Compile / mainClass := Some("pl.tomaszgigiel.everythingeverywhereallatonce.SimpleScalaApp"),
    assembly / assemblyJarName := s"${name.value}-uberjar.jar",
    assembly / assemblyMergeStrategy := {
      case PathList(ps@_*) if ps.last == "module-info.class" => MergeStrategy.discard
      case x =>
        val oldStrategy = (assembly / assemblyMergeStrategy).value
        oldStrategy(x)
    }
  )
