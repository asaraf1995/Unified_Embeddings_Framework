ThisBuild / scalaVersion := "2.12.7"
ThisBuild / organization := "New"

lazy val hello = (project in file("."))
  .settings(
    name := "Unified_Embeddings_Platform",
	crossPaths := false,
javacOptions += "-g:none",
    resolvers += Resolver.jcenterRepo,
//    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.7" % Test,
    libraryDependencies ++= Seq(
      "net.aichler" % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test
    ),
    libraryDependencies += "net.aichler" % "jupiter-interface" % "0.8.4" % Test,
    libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.20",
   libraryDependencies += "junit" % "junit" % "4.12" % "Test", // already added
libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
  )
