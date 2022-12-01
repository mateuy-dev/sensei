package dev.mateuy.katas.sensei.output

import kotlinx.html.div
import kotlinx.html.stream.createHTML
import kotlinx.html.table
import kotlinx.html.td
import kotlinx.html.tr

fun defaultOutputTypeFor(value : Any?) : OutputType<*> {
    if(value == null) return NullOutput
    return if(value.isANonEmptyMatrix())
        SparseMatrixOutput
    else if(value.isAList())
        VerticalListOutput
    else
        StringOutput
}