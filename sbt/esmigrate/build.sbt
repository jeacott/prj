//name := "es-migrate"

version := "0.1-SNAPSHOT"

// scalaVersion := "2.10.3"

autoScalaLibrary := false

crossPaths := false

scalacOptions ++= Seq("-encoding", "UTF-8", "-Xlint", "-deprecation", "-unchecked", "-Xfatal-warnings")

javacOptions ++= Seq("-encoding", "UTF-8", "-Xlint:unchecked", "-Xlint:deprecation")

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-a")

parallelExecution in Test := false

//logLevel := Level.Debug

// http://www.scala-sbt.org/release/docs/Howto/package.html
//packageOptions in (Compile, packageBin) += Package.ManifestAttributes(java.util.jar.Attributes.Name.CLASS_PATH -> "org.snu.ids.ha.jar" )

libraryDependencies ++= Seq(
    "org.slf4j"                 % "slf4j-api"      % "1.7.5",
    "org.slf4j"                 % "slf4j-log4j12"  % "1.7.5",
    "log4j"                     % "log4j"          % "1.2.17",
    "org.elasticsearch"         % "elasticsearch"  % "1.4.0",
    // one-jar stuff
    "commons-lang"              % "commons-lang"   % "2.6",
    "commons-cli"               % "commons-cli"    % "1.2",
    // testing stuff
    "com.novocode"              % "junit-interface"    % "0.10"    % "test",
    "junit"                     % "junit"              % "4.12"    % "test",
)

//unmanagedJars in Compile ++= {
    //(file("D:/.../lib") ** "*.jar").classpath

    //val base = baseDirectory.value
    //val baseDirectories = (base / "libA") +++ (base / "b" / "lib") +++ (base / "libC")
    //val customJars = (baseDirectories ** "*.jar") +++ (base / "d" / "my.jar")
    //customJars.classpath
//}
