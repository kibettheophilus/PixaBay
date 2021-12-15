package dev.kibet.data_local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IdConverters {

    private val gson = Gson()

    @TypeConverter
    fun fromList(ids: List<Int>): String {
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.toJson(ids, type)
    }

    @TypeConverter
    fun toList(ids: String?): List<Int> {
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(ids, type)
    }
}