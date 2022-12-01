package dev.mateuy.katas.sensei

import kotlinx.html.body
import kotlinx.html.html
import kotlinx.html.stream.HTMLStreamBuilder
import kotlinx.html.stream.appendHTML
import kotlinx.html.stream.createHTML
import kotlinx.html.table
import kotlinx.html.*
import java.nio.file.Path
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.io.path.absolute
import kotlin.io.path.readLines

data class CodeLine(val lineNumber : Int, val path : Path){
    companion object {
        fun current() : CodeLine {
            val throwable = Throwable()
            // We need to skip the stackTraces of the sensei calls
            val stackTrace = throwable.stackTrace.first { !it.className.contains("dev.mateuy.katas.sensei") }
            val lineNumber = stackTrace.lineNumber
            val fileName = stackTrace.fileName
            val className = stackTrace.className
            val currentPackage = className.split(".").dropLast(1).joinToString("/")
            val pathString = "src/main/kotlin/"+ currentPackage.replace(".", "/") + "/" + fileName
            val path = Path.of(pathString)
            return CodeLine(lineNumber, path)
        }
    }

    fun getLine() = path.absolute().readLines()[lineNumber-1]

    fun withNextLine() = copy(lineNumber = lineNumber+1)

}











