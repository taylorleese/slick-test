name := "slick-test"

organization := "com.xyz"

scalaVersion := "2.10.2"

libraryDependencies ++= {
  Seq(
    "com.typesafe.slick" %% "slick" % "1.0.1"
  )
}

logBuffered := false
