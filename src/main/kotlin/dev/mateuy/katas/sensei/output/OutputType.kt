package dev.mateuy.katas.sensei.output

import kotlinx.html.*
import kotlinx.html.stream.createHTML

sealed interface OutputType<T> {
    fun outputStringFor(value: Any?) = outputString(value as T)
    fun outputString(value: T): String
}

object NullOutput : OutputType<Any?>{
    override fun outputString(value: Any?): String = StringOutput.outputString("null")
}

object SparseMatrixOutput : OutputType<List<List<*>>> {
    override fun outputString(value: List<List<*>>): String = createHTML().div("sensei-output") {
        table("table table-striped w-auto") {
            value.forEach { row ->
                tr {
                    td {
                        table {
                            tr {
                                row.forEach { cell ->
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
}

object MatrixOutput : OutputType<List<List<*>>> {
    override fun outputString(value: List<List<*>>): String = createHTML().div("sensei-output") {
        table("table table-striped w-auto") {
            value.forEach { row ->
                tr {
                    row.forEach { cell ->
                        td {
                            +cell.toString()
                        }
                    }
                }
            }
        }
    }
}


object VerticalListOutput : OutputType<List<*>> {
    override fun outputString(value: List<*>) = MatrixOutput.outputString(value.map { listOf(it) })
}

object StringOutput : OutputType<Any> {
    override fun outputString(value: Any) = createHTML().div("sensei-output") {
        table("table table-striped w-auto") {
            tr {
                td {
                    +value.toString()
                }
            }
        }
    }
}