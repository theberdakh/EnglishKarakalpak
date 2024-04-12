package com.theberdakh.tradingglossary.data

import android.content.Context
import com.google.gson.GsonBuilder
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

fun Context.jsonToString(name: String) = this.assets.open(name).bufferedReader().use { it.readText() }

fun convertJsonString(jsonString: String): Array<Word> {
    val gsonBuilder = GsonBuilder()
    val gson = gsonBuilder.create()

    return gson.fromJson(jsonString, Array<Word>::class.java)
}

fun convertJsonUsingSerialisation(jsonString: String): List<Word>{
   return Json.decodeFromString(jsonString)
}