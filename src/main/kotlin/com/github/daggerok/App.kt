package com.github.daggerok

/*import java.io.File
import java.lang.ProcessBuilder.Redirect.INHERIT
import java.nio.file.Paths
import java.util.concurrent.TimeUnit.SECONDS

fun String.runIn(workDir: File) =
    ProcessBuilder(*split(" ").toTypedArray())
        .directory(workDir)
        .redirectOutput(INHERIT)
        .redirectError(INHERIT)
        .start()
        .waitFor(60, SECONDS)

fun main(args: Array<String>) {

  if (args.isEmpty()) {
    println("nothing to run...")
    return
  }

  val command = args.toList().joinToString { " " }
  println("running a command: '$command'")

  val workDir = Paths.get("").toAbsolutePath().toFile()
  try { command.runIn(workDir) }
  catch (e: Throwable) { println("shit happens: ${e.localizedMessage}") }
}*/

fun main() {}
