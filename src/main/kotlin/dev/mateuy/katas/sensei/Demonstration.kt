package dev.mateuy.katas.sensei

import dev.mateuy.katas.sensei.output.OutputType
import dev.mateuy.katas.sensei.output.defaultOutputTypeFor
import dev.mateuy.katas.sensei.output.isAList
import dev.mateuy.katas.sensei.output.isANonEmptyMatrix
import kotlinx.html.stream.createHTML
import kotlinx.html.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class Demonstration(){
    val stringBuilder = StringBuilder()
    
    fun text(body: ()-> String){
        val output = body()
        stringBuilder.appendLine(output)
        stringBuilder.appendLine()
    }

    fun codeAndOutput(outputType : OutputType<*>? = null, body: () -> Any?){
        code({})
        output(outputType, body)
    }

    fun codeNextLine() = code{}

    fun code(body: ()->Any?){
        val codeStartLine = CodeLine.current()
        stringBuilder.appendLine("```kotlin")
        stringBuilder.appendLine(codeStartLine.withNextLine().getLine().trim())
        stringBuilder.appendLine("```")
        stringBuilder.appendLine()
    }
    
    fun output(outputType : OutputType<*>? = null, body: () -> Any? ){
        val result = body()
        val output = outputString(defaultOutputTypeFor(result), result)
        stringBuilder.appendLine(output)
        stringBuilder.appendLine()
    }

    private fun outputString(outputType: OutputType<*>, result: Any?) :String =
        outputType.outputStringFor(result)

    fun fullReport() = stringBuilder.toString()
}

