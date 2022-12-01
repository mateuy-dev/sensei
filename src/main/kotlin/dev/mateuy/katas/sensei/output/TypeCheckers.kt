package dev.mateuy.katas.sensei.output

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
internal fun Any.isAList() : Boolean{
    contract { returns(true) implies (this@isAList is List<*>) }
    return this is kotlin.collections.List<*>
}


@OptIn(ExperimentalContracts::class)
internal fun Any.isANotEmptyList() : Boolean{
    contract { returns(true) implies (this@isANotEmptyList is List<*>) }
    if(!this.isAList()) return false
    return !isEmpty()
}

internal fun Any.isANonEmptyMatrix(): Boolean {
    if(!isANotEmptyList()) return false
    return first() is List<*>
}

internal fun List<*>.isANonEmptyListOfString(): Boolean {
    if(!isANotEmptyList()) return false
    return first() is String
}