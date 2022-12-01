package dev.mateuy.katas.sensei

import java.nio.file.Path
import kotlin.io.path.writeText

fun demonstrate(path: Path? = null, body: Demonstration.()->Any) {
    val demonstration = Demonstration()
    demonstration.body()
    val report = demonstration.fullReport()
    println(demonstration.fullReport())

    path?.writeText(report)
}