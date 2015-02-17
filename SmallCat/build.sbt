name := "SmallCat"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.34",
  "com.memetix" % "microsoft-translator-java-api" % "0.6.2"
)

play.Project.playJavaSettings
