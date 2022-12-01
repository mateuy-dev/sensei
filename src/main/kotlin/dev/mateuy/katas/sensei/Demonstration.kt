package dev.mateuy.katas.sensei

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

    fun codeAndOutput(outputOptions: OutputOptions = OutputOptions(), body: () -> Any?){
        code()
        output(outputOptions, body)
    }

    fun code(body: ()->Any? = {}){
        val codeStartLine = CodeLine.current()
        stringBuilder.appendLine("```kotlin")
        stringBuilder.appendLine(codeStartLine.withNextLine().getLine().trim())
        stringBuilder.appendLine("```")
        stringBuilder.appendLine()
    }
    
    fun output(outputOptions: OutputOptions = OutputOptions(), body: () -> Any? ){
        val output = outputString(outputOptions, body())
        stringBuilder.appendLine(output)
        stringBuilder.appendLine()
    }

    private fun outputString(outputOptions: OutputOptions, result: Any?) :String {
        if(result == null) return "null"
        return if(result.isANonEmptyMatrix())
            outputSparceMatrixString(result as List<List<*>>)
        else if(result.isAList())
            outputListString(outputOptions, result as List<*>)
        else
            createHTML().div("sensei-output") {
                table("table table-striped w-auto") {
                    tr{
                        td{
                            +result.toString()
                        }
                    }
                }
            }

    }

    fun outputListString(outputOptions: OutputOptions, result: List<*>) : String =
        outputMatrixString(result.map{ listOf(it) })

    fun outputSparceMatrixString(result: List<List<*>>) : String =
        createHTML().div("sensei-output"){
            table("table table-striped w-auto") {
                result.forEach{ row ->
                    tr{
                        td{
                            table{
                                tr{
                                    row.forEach{ cell ->

                                        td {
                                            +cell.toString()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    fun outputMatrixString(result: List<List<*>>) : String =
        createHTML().div("sensei-output"){
            table("table table-striped w-auto") {
                result.forEach{ row ->
                    tr{
                        row.forEach{ cell ->
                            td {
                                +cell.toString()
                            }
                        }
                    }
                }
            }
        }
    fun fullReport() = stringBuilder.toString()
}

@OptIn(ExperimentalContracts::class)
private fun Any.isAList() : Boolean{
    contract { returns(true) implies (this@isAList is List<*>) }
    return this is kotlin.collections.List<*>
}


@OptIn(ExperimentalContracts::class)
private fun Any.isANotEmptyList() : Boolean{
    contract { returns(true) implies (this@isANotEmptyList is List<*>) }
    if(!this.isAList()) return false
    return !isEmpty()
}

private fun Any.isANonEmptyMatrix(): Boolean {
    if(!isANotEmptyList()) return false
    return first() is List<*>
}

private fun List<*>.isANonEmptyListOfString(): Boolean {
    if(!isANotEmptyList()) return false
    return first() is String
}