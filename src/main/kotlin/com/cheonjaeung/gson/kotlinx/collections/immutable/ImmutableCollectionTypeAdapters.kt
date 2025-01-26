package com.cheonjaeung.gson.kotlinx.collections.immutable

import com.google.gson.JsonElement
import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.internal.JsonReaderInternalAccess
import com.google.gson.internal.Streams
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import kotlinx.collections.immutable.*

abstract class BaseImmutableCollectionTypeAdapter<C : Iterable<E>?, E>(
    private val elementTypeAdapter: TypeAdapter<E>
) : TypeAdapter<C>() {

    override fun write(writer: JsonWriter?, value: C) {
        if (writer == null) {
            return
        }

        if (value == null) {
            writer.nullValue()
            return
        }

        writer.beginArray()
        for (element in value) {
            elementTypeAdapter.write(writer, element)
        }
        writer.endArray()
    }

    override fun read(reader: JsonReader?): C? {
        if (reader == null) {
            return null
        }

        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return null
        }

        reader.beginArray()
        val collection = buildCollection { builder ->
            while (reader.hasNext()) {
                val element = elementTypeAdapter.read(reader)
                builder.add(element)
            }
        }
        reader.endArray()

        return collection
    }

    abstract fun buildCollection(action: (MutableCollection<E>) -> Unit): C
}

class ImmutableCollectionTypeAdapter<E>(
    elementTypeAdapter: TypeAdapter<E>
) : BaseImmutableCollectionTypeAdapter<ImmutableCollection<E>, E>(elementTypeAdapter) {

    override fun buildCollection(action: (MutableCollection<E>) -> Unit): ImmutableCollection<E> {
        return persistentListOf<E>().builder().apply(action).build()
    }
}

class ImmutableListTypeAdapter<E>(
    elementTypeAdapter: TypeAdapter<E>
) : BaseImmutableCollectionTypeAdapter<ImmutableList<E>, E>(elementTypeAdapter) {

    override fun buildCollection(action: (MutableCollection<E>) -> Unit): ImmutableList<E> {
        return persistentListOf<E>().builder().apply(action).build()
    }
}

class ImmutableSetTypeAdapter<E>(
    elementTypeAdapter: TypeAdapter<E>
) : BaseImmutableCollectionTypeAdapter<ImmutableSet<E>, E>(elementTypeAdapter) {

    override fun buildCollection(action: (MutableCollection<E>) -> Unit): ImmutableSet<E> {
        return persistentSetOf<E>().builder().apply(action).build()
    }
}

class ImmutableMapTypeAdapter<K, V>(
    private val keyTypeAdapter: TypeAdapter<K>,
    private val valueTypeAdapter: TypeAdapter<V>
) : TypeAdapter<ImmutableMap<K, V>>() {
    override fun write(writer: JsonWriter?, value: ImmutableMap<K, V>?) {
        if (writer == null) {
            return
        }

        if (value == null) {
            writer.nullValue()
            return
        }

        val keys: MutableList<JsonElement> = ArrayList(value.size)
        val values: MutableList<V> = ArrayList(value.size)
        var hasComplexKeys = false
        for (entry in value) {
            val key = keyTypeAdapter.toJsonTree(entry.key)
            keys.add(key)
            values.add(entry.value)
            hasComplexKeys = hasComplexKeys || key.isJsonArray || key.isJsonObject
        }

        writer.beginObject()
        if (hasComplexKeys) {
            for (i in keys.indices) {
                writer.beginArray()
                Streams.write(keys[i], writer)
                valueTypeAdapter.write(writer, values[i])
                writer.endArray()
            }
        } else {
            for (i in keys.indices) {
                writer.name(convertKeyToString(keys[i]))
                valueTypeAdapter.write(writer, values[i])
            }
        }
        writer.endObject()
    }

    private fun convertKeyToString(keyElement: JsonElement): String {
        return if (keyElement.isJsonPrimitive) {
            val primitive = keyElement.asJsonPrimitive
            when {
                primitive.isBoolean -> primitive.asBoolean.toString()
                primitive.isBoolean -> primitive.asNumber.toString()
                primitive.isString -> primitive.asString
                else -> throw IllegalArgumentException("Cannot convert key '$keyElement' to string")
            }
        } else if (keyElement.isJsonNull) {
            return "null"
        } else {
            throw IllegalArgumentException("Cannot convert key '$keyElement' to string")
        }
    }

    override fun read(reader: JsonReader?): ImmutableMap<K, V>? {
        if (reader == null) {
            return null
        }

        val peek = reader.peek()
        if (peek == JsonToken.NULL) {
            reader.nextNull()
            return null
        }

        val builder = persistentMapOf<K, V>().builder()

        if (peek == JsonToken.BEGIN_ARRAY) {
            reader.beginArray()
            while (reader.hasNext()) {
                reader.beginArray()
                val key = keyTypeAdapter.read(reader)
                val value = valueTypeAdapter.read(reader)
                val replaced = builder.put(key, value)
                if (replaced != null) {
                    throw JsonSyntaxException("duplicate key: $key")
                }
                reader.endArray()
            }
            reader.endArray()
        } else {
            reader.beginObject()
            while (reader.hasNext()) {
                JsonReaderInternalAccess.INSTANCE.promoteNameToValue(reader)
                val key = keyTypeAdapter.read(reader)
                val value = valueTypeAdapter.read(reader)
                val replaced = builder.put(key, value)
                if (replaced != null) {
                    throw JsonSyntaxException("duplicate key: $key")
                }
            }
            reader.endObject()
        }

        return builder.build()
    }
}
