package com.cheonjaeung.gson.kotlinx.collections.immutable

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import kotlinx.collections.immutable.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * A [TypeAdapterFactory] that creates immutable collection type adapters.
 *
 * # Supported Types
 *
 * - [ImmutableCollection]
 * - [ImmutableList]
 * - [ImmutableSet]
 * - [ImmutableMap]
 * - [PersistentCollection]
 * - [PersistentList]
 * - [PersistentSet]
 * - [PersistentMap]
 *
 * The factory must be registered with [GsonBuilder][com.google.gson.GsonBuilder]:
 *
 * ```
 * val builder = GsonBuilder()
 * builder.registerTypeAdapterFactory(ImmutableCollectionTypeAdapterFactory())
 * ...
 * val gson = builder.create()
 * ```
 */
class ImmutableCollectionTypeAdapterFactory(
    private val complexMapKeySerialization: Boolean = false
) : TypeAdapterFactory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any?> create(gson: Gson?, typeToken: TypeToken<T>?): TypeAdapter<T>? {
        if (gson == null || typeToken == null) {
            return null
        }

        val type = typeToken.type
        return when (typeToken.rawType) {
            ImmutableList::class.java,
            PersistentList::class.java -> {
                val elementType = getCollectionElementType(type)
                val elementTypeAdapter = gson.getAdapter(TypeToken.get(elementType))
                return ImmutableListTypeAdapter(elementTypeAdapter) as TypeAdapter<T>
            }

            ImmutableSet::class.java,
            PersistentSet::class.java -> {
                val elementType = getCollectionElementType(type)
                val elementTypeAdapter = gson.getAdapter(TypeToken.get(elementType))
                return ImmutableSetTypeAdapter(elementTypeAdapter) as TypeAdapter<T>
            }

            ImmutableCollection::class.java,
            PersistentCollection::class.java -> {
                val elementType = getCollectionElementType(type)
                val elementTypeAdapter = gson.getAdapter(TypeToken.get(elementType))
                return ImmutableCollectionTypeAdapter(elementTypeAdapter) as TypeAdapter<T>
            }

            ImmutableMap::class.java,
            PersistentMap::class.java -> {
                val (keyType, valueType) = getMapKeyValueType(type)
                val keyTypeAdapter = gson.getAdapter(TypeToken.get(keyType))
                val valueTypeAdapter = gson.getAdapter(TypeToken.get(valueType))
                return ImmutableMapTypeAdapter(keyTypeAdapter, valueTypeAdapter, complexMapKeySerialization) as TypeAdapter<T>
            }

            else -> null
        }
    }

    /**
     * Returns element type of the given collection type. If the [type] is not a generic type,
     * it returns `Any` type (`Object` in Java).
     *
     * For example, if a type is `ImmutableList<Int>`, this function returns `Int` type.
     */
    private fun getCollectionElementType(type: Type): Type {
        if (type is ParameterizedType) {
            return type.actualTypeArguments[0]
        }
        return Any::class.java
    }

    /**
     * Returns a pair of key/value type of the given map type. If the [type] is not a generic type,
     * it returns a pair of two `Any` types (`Object` in Java).
     *
     * For example, if a type is `ImmutableMap<String, Int>`, this function returns pair of `String`
     * type and `Int` type.
     */
    private fun getMapKeyValueType(type: Type): Pair<Type, Type> {
        if (type is ParameterizedType) {
            return type.actualTypeArguments[0] to type.actualTypeArguments[1]
        }
        return Pair(Any::class.java, Any::class.java)
    }
}
