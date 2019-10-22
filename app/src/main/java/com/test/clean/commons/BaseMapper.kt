package com.test.clean.commons

interface BaseMapper<in A, out B> {

    fun map(type: A?): B
}
