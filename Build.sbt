val scalaVersion   = "2.11.0" // or "2.10.4"
val libraryVersion = "0.4.0"  // or "0.5-SNAPSHOT"

resolvers ++= Seq(
  "Sonatype OSS Releases"  at "http://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
)

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.0.6",
  "org.scalaz" %% "scalaz-concurrent" % "7.0.6",
  "org.json4s" %% "json4s-native" % "3.2.5",
  "org.json4s" %% "json4s-jackson" % "3.2.5",
  "com.typesafe.slick" %% "slick" % "2.0.2",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.github.julien-truffaut"  %%  "monocle-core"    % libraryVersion,
  "com.github.julien-truffaut"  %%  "monocle-generic" % libraryVersion,
  "com.github.julien-truffaut"  %%  "monocle-macro"   % libraryVersion,       // since 0.4.0
  "com.github.julien-truffaut"  %%  "monocle-law"     % libraryVersion % "test" // since 0.4.0  
)