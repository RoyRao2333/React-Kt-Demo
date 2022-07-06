package com.royrao.reactdemo.components

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import java.text.SimpleDateFormat

object JMapper {
    val default: JsonMapper = JsonMapper
        .builder()
        .defaultDateFormat(SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .serializationInclusion(JsonInclude.Include.NON_NULL)
        .build()
}


// Shared Methods -

fun JsonNode.getShallowKeys(): List<String> {
    val keys = mutableListOf<String>()
    this.fieldNames().forEach {
        keys.add(it)
    }

    return keys
}

fun JsonNode.getDeepKeys(): List<String> {
    return findKeys(this, mutableListOf())
}


// Private Methods -

private fun findKeys(node: JsonNode, keys: MutableList<String>): List<String> {
    node.fields().forEach {
        if (it.value.isObject) {
            findKeys(it.value, keys)
        } else if (it.value.isArray) {
            it.value.forEach { n ->
                findKeys(n, keys)
            }
        }

        keys.add(it.key)
    }

    return keys
}

