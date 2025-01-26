package com.cheonjaeung.gson.kotlinx.collections.immutable

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import kotlinx.collections.immutable.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ImmutableCollectionTypeAdapterFactoryTest {

    @Test
    fun testPrimitiveDeserialization() {
        val gson = GsonBuilder()
            .registerTypeAdapterFactory(ImmutableCollectionTypeAdapterFactory())
            .create()

        val deserialized = gson.fromJson(jsonString, Expected::class.java)

        assertEquals(expectedObject, deserialized)
    }

    @Test
    fun testPrimitiveSerialization() {
        val gson = GsonBuilder()
            .registerTypeAdapterFactory(ImmutableCollectionTypeAdapterFactory())
            .create()

        val serialized = gson.toJson(expectedObject)

        assertEquals(
            jsonString.removeWhitespacesAndNewlines(),
            serialized.removeWhitespacesAndNewlines()
        )
    }

    private fun String.removeWhitespacesAndNewlines(): String = this.replace("\\s+".toRegex(), "")

    companion object {
        private val jsonString = """
            {
                "short": {
                    "immutable": {
                        "list": [1, 2, 3, 4, 5],
                        "collection": [1, 2, 3, 4, 5],
                        "set": [1, 2, 3, 4, 5],
                        "map": {
                            "1": 1,
                            "2": 2,
                            "3": 3,
                            "4": 4,
                            "5": 5
                        }
                    },
                    "persistent": {
                        "list": [1, 2, 3, 4, 5],
                        "collection": [1, 2, 3, 4, 5],
                        "set": [1, 2, 3, 4, 5],
                        "map": {
                            "1": 1,
                            "2": 2,
                            "3": 3,
                            "4": 4,
                            "5": 5
                        }
                    }
                },
                "integer": {
                    "immutable": {
                        "list": [1, 2, 3, 4, 5],
                        "collection": [1, 2, 3, 4, 5],
                        "set": [1, 2, 3, 4, 5],
                        "map": {
                            "1": 1,
                            "2": 2,
                            "3": 3,
                            "4": 4,
                            "5": 5
                        }
                    },
                    "persistent": {
                        "list": [1, 2, 3, 4, 5],
                        "collection": [1, 2, 3, 4, 5],
                        "set": [1, 2, 3, 4, 5],
                        "map": {
                            "1": 1,
                            "2": 2,
                            "3": 3,
                            "4": 4,
                            "5": 5
                        }
                    }
                },
                "long": {
                    "immutable": {
                        "list": [1, 2, 3, 4, 5],
                        "collection": [1, 2, 3, 4, 5],
                        "set": [1, 2, 3, 4, 5],
                        "map": {
                            "1": 1,
                            "2": 2,
                            "3": 3,
                            "4": 4,
                            "5": 5
                        }
                    },
                    "persistent": {
                        "list": [1, 2, 3, 4, 5],
                        "collection": [1, 2, 3, 4, 5],
                        "set": [1, 2, 3, 4, 5],
                        "map": {
                            "1": 1,
                            "2": 2,
                            "3": 3,
                            "4": 4,
                            "5": 5
                        }
                    }
                },
                "float": {
                    "immutable": {
                        "list": [1.0, 2.0, 3.0, 4.0, 5.0],
                        "collection": [1.0, 2.0, 3.0, 4.0, 5.0],
                        "set": [1.0, 2.0, 3.0, 4.0, 5.0],
                        "map": {
                            "1": 1.0,
                            "2": 2.0,
                            "3": 3.0,
                            "4": 4.0,
                            "5": 5.0
                        }
                    },
                    "persistent": {
                        "list": [1.0, 2.0, 3.0, 4.0, 5.0],
                        "collection": [1.0, 2.0, 3.0, 4.0, 5.0],
                        "set": [1.0, 2.0, 3.0, 4.0, 5.0],
                        "map": {
                            "1": 1.0,
                            "2": 2.0,
                            "3": 3.0,
                            "4": 4.0,
                            "5": 5.0
                        }
                    }
                },
                "double": {
                    "immutable": {
                        "list": [1.0, 2.0, 3.0, 4.0, 5.0],
                        "collection": [1.0, 2.0, 3.0, 4.0, 5.0],
                        "set": [1.0, 2.0, 3.0, 4.0, 5.0],
                        "map": {
                            "1": 1.0,
                            "2": 2.0,
                            "3": 3.0,
                            "4": 4.0,
                            "5": 5.0
                        }
                    },
                    "persistent": {
                        "list": [1.0, 2.0, 3.0, 4.0, 5.0],
                        "collection": [1.0, 2.0, 3.0, 4.0, 5.0],
                        "set": [1.0, 2.0, 3.0, 4.0, 5.0],
                        "map": {
                            "1": 1.0,
                            "2": 2.0,
                            "3": 3.0,
                            "4": 4.0,
                            "5": 5.0
                        }
                    }
                },
                "char": {
                    "immutable": {
                        "list": ["a", "b", "c", "d", "e"],
                        "collection": ["a", "b", "c", "d", "e"],
                        "set": ["a", "b", "c", "d", "e"],
                        "map": {
                            "1": "a",
                            "2": "b",
                            "3": "c",
                            "4": "d",
                            "5": "e"
                        }
                    },
                    "persistent": {
                        "list": ["a", "b", "c", "d", "e"],
                        "collection": ["a", "b", "c", "d", "e"],
                        "set": ["a", "b", "c", "d", "e"],
                        "map": {
                            "1": "a",
                            "2": "b",
                            "3": "c",
                            "4": "d",
                            "5": "e"
                        }
                    }
                },
                "string": {
                    "immutable": {
                        "list": ["a", "b", "c", "d", "e"],
                        "collection": ["a", "b", "c", "d", "e"],
                        "set": ["a", "b", "c", "d", "e"],
                        "map": {
                            "1": "a",
                            "2": "b",
                            "3": "c",
                            "4": "d",
                            "5": "e"
                        }
                    },
                    "persistent": {
                        "list": ["a", "b", "c", "d", "e"],
                        "collection": ["a", "b", "c", "d", "e"],
                        "set": ["a", "b", "c", "d", "e"],
                        "map": {
                            "1": "a",
                            "2": "b",
                            "3": "c",
                            "4": "d",
                            "5": "e"
                        }
                    }
                },
                "object": {
                    "immutable": {
                        "list": [
                            {
                                 "string": "value1",
                                 "numbers": [1, 2, 3]
                            },
                            {
                                 "string": "value2",
                                 "numbers": [1, 2, 3]
                            }
                        ],
                        "collection": [
                            {
                                 "string": "value3",
                                 "numbers": [1, 2, 3]
                            },
                            {
                                 "string": "value4",
                                 "numbers": [1, 2, 3]
                            }
                        ],
                        "set": [
                            {
                                 "string": "value5",
                                 "numbers": [1, 2, 3]
                            },
                            {
                                 "string": "value6",
                                 "numbers": [1, 2, 3]
                            }
                        ],
                        "map": {
                            "1": {
                                 "string": "value7",
                                 "numbers": [1, 2, 3]
                            },
                            "2": {
                                 "string": "value8",
                                 "numbers": [1, 2, 3]
                            }
                        }
                    },
                    "persistent": {
                        "list": [
                            {
                                 "string": "value1",
                                 "numbers": [1, 2, 3]
                            },
                            {
                                 "string": "value2",
                                 "numbers": [1, 2, 3]
                            }
                        ],
                        "collection": [
                            {
                                 "string": "value3",
                                 "numbers": [1, 2, 3]
                            },
                            {
                                 "string": "value4",
                                 "numbers": [1, 2, 3]
                            }
                        ],
                        "set": [
                            {
                                 "string": "value5",
                                 "numbers": [1, 2, 3]
                            },
                            {
                                 "string": "value6",
                                 "numbers": [1, 2, 3]
                            }
                        ],
                        "map": {
                            "1": {
                                 "string": "value7",
                                 "numbers": [1, 2, 3]
                            },
                            "2": {
                                 "string": "value8",
                                 "numbers": [1, 2, 3]
                            }
                        }
                    }
                }
            }
        """.trimIndent()

        private val expectedObject = Expected(
            short = Expected.TypedContent(
                immutable = Expected.Immutable(
                    list = persistentListOf(1, 2, 3, 4, 5),
                    collection = persistentListOf(1, 2, 3, 4, 5),
                    set = persistentSetOf(1, 2, 3, 4, 5),
                    map = persistentMapOf(
                        "1" to 1,
                        "2" to 2,
                        "3" to 3,
                        "4" to 4,
                        "5" to 5
                    )
                ),
                persistent = Expected.Persistent(
                    list = persistentListOf(1, 2, 3, 4, 5),
                    collection = persistentListOf(1, 2, 3, 4, 5),
                    set = persistentSetOf(1, 2, 3, 4, 5),
                    map = persistentMapOf(
                        "1" to 1,
                        "2" to 2,
                        "3" to 3,
                        "4" to 4,
                        "5" to 5
                    )
                )
            ),
            integer = Expected.TypedContent(
                immutable = Expected.Immutable(
                    list = persistentListOf(1, 2, 3, 4, 5),
                    collection = persistentListOf(1, 2, 3, 4, 5),
                    set = persistentSetOf(1, 2, 3, 4, 5),
                    map = persistentMapOf(
                        "1" to 1,
                        "2" to 2,
                        "3" to 3,
                        "4" to 4,
                        "5" to 5
                    )
                ),
                persistent = Expected.Persistent(
                    list = persistentListOf(1, 2, 3, 4, 5),
                    collection = persistentListOf(1, 2, 3, 4, 5),
                    set = persistentSetOf(1, 2, 3, 4, 5),
                    map = persistentMapOf(
                        "1" to 1,
                        "2" to 2,
                        "3" to 3,
                        "4" to 4,
                        "5" to 5
                    )
                )
            ),
            long = Expected.TypedContent(
                immutable = Expected.Immutable(
                    list = persistentListOf(1, 2, 3, 4, 5),
                    collection = persistentListOf(1, 2, 3, 4, 5),
                    set = persistentSetOf(1, 2, 3, 4, 5),
                    map = persistentMapOf(
                        "1" to 1,
                        "2" to 2,
                        "3" to 3,
                        "4" to 4,
                        "5" to 5
                    )
                ),
                persistent = Expected.Persistent(
                    list = persistentListOf(1, 2, 3, 4, 5),
                    collection = persistentListOf(1, 2, 3, 4, 5),
                    set = persistentSetOf(1, 2, 3, 4, 5),
                    map = persistentMapOf(
                        "1" to 1,
                        "2" to 2,
                        "3" to 3,
                        "4" to 4,
                        "5" to 5
                    )
                )
            ),
            float = Expected.TypedContent(
                immutable = Expected.Immutable(
                    list = persistentListOf(1f, 2f, 3f, 4f, 5f),
                    collection = persistentListOf(1f, 2f, 3f, 4f, 5f),
                    set = persistentSetOf(1f, 2f, 3f, 4f, 5f),
                    map = persistentMapOf(
                        "1" to 1f,
                        "2" to 2f,
                        "3" to 3f,
                        "4" to 4f,
                        "5" to 5f
                    )
                ),
                persistent = Expected.Persistent(
                    list = persistentListOf(1f, 2f, 3f, 4f, 5f),
                    collection = persistentListOf(1f, 2f, 3f, 4f, 5f),
                    set = persistentSetOf(1f, 2f, 3f, 4f, 5f),
                    map = persistentMapOf(
                        "1" to 1f,
                        "2" to 2f,
                        "3" to 3f,
                        "4" to 4f,
                        "5" to 5f
                    )
                )
            ),
            double = Expected.TypedContent(
                immutable = Expected.Immutable(
                    list = persistentListOf(1.0, 2.0, 3.0, 4.0, 5.0),
                    collection = persistentListOf(1.0, 2.0, 3.0, 4.0, 5.0),
                    set = persistentSetOf(1.0, 2.0, 3.0, 4.0, 5.0),
                    map = persistentMapOf(
                        "1" to 1.0,
                        "2" to 2.0,
                        "3" to 3.0,
                        "4" to 4.0,
                        "5" to 5.0
                    )
                ),
                persistent = Expected.Persistent(
                    list = persistentListOf(1.0, 2.0, 3.0, 4.0, 5.0),
                    collection = persistentListOf(1.0, 2.0, 3.0, 4.0, 5.0),
                    set = persistentSetOf(1.0, 2.0, 3.0, 4.0, 5.0),
                    map = persistentMapOf(
                        "1" to 1.0,
                        "2" to 2.0,
                        "3" to 3.0,
                        "4" to 4.0,
                        "5" to 5.0
                    )
                )
            ),
            char = Expected.TypedContent(
                immutable = Expected.Immutable(
                    list = persistentListOf('a', 'b', 'c', 'd', 'e'),
                    collection = persistentListOf('a', 'b', 'c', 'd', 'e'),
                    set = persistentSetOf('a', 'b', 'c', 'd', 'e'),
                    map = persistentMapOf(
                        "1" to 'a',
                        "2" to 'b',
                        "3" to 'c',
                        "4" to 'd',
                        "5" to 'e'
                    )
                ),
                persistent = Expected.Persistent(
                    list = persistentListOf('a', 'b', 'c', 'd', 'e'),
                    collection = persistentListOf('a', 'b', 'c', 'd', 'e'),
                    set = persistentSetOf('a', 'b', 'c', 'd', 'e'),
                    map = persistentMapOf(
                        "1" to 'a',
                        "2" to 'b',
                        "3" to 'c',
                        "4" to 'd',
                        "5" to 'e'
                    )
                )
            ),
            string = Expected.TypedContent(
                immutable = Expected.Immutable(
                    list = persistentListOf("a", "b", "c", "d", "e"),
                    collection = persistentListOf("a", "b", "c", "d", "e"),
                    set = persistentSetOf("a", "b", "c", "d", "e"),
                    map = persistentMapOf(
                        "1" to "a",
                        "2" to "b",
                        "3" to "c",
                        "4" to "d",
                        "5" to "e"
                    )
                ),
                persistent = Expected.Persistent(
                    list = persistentListOf("a", "b", "c", "d", "e"),
                    collection = persistentListOf("a", "b", "c", "d", "e"),
                    set = persistentSetOf("a", "b", "c", "d", "e"),
                    map = persistentMapOf(
                        "1" to "a",
                        "2" to "b",
                        "3" to "c",
                        "4" to "d",
                        "5" to "e"
                    )
                )
            ),
            objects = Expected.TypedContent(
                immutable = Expected.Immutable(
                    list = persistentListOf(
                        Expected.TestObject("value1", persistentListOf(1, 2, 3)),
                        Expected.TestObject("value2", persistentListOf(1, 2, 3))
                    ),
                    collection = persistentListOf(
                        Expected.TestObject("value3", persistentListOf(1, 2, 3)),
                        Expected.TestObject("value4", persistentListOf(1, 2, 3))
                    ),
                    set = persistentSetOf(
                        Expected.TestObject("value5", persistentListOf(1, 2, 3)),
                        Expected.TestObject("value6", persistentListOf(1, 2, 3))
                    ),
                    map = persistentMapOf(
                        "1" to Expected.TestObject("value7", persistentListOf(1, 2, 3)),
                        "2" to Expected.TestObject("value8", persistentListOf(1, 2, 3))
                    )
                ),
                persistent = Expected.Persistent(
                    list = persistentListOf(
                        Expected.TestObject("value1", persistentListOf(1, 2, 3)),
                        Expected.TestObject("value2", persistentListOf(1, 2, 3))
                    ),
                    collection = persistentListOf(
                        Expected.TestObject("value3", persistentListOf(1, 2, 3)),
                        Expected.TestObject("value4", persistentListOf(1, 2, 3))
                    ),
                    set = persistentSetOf(
                        Expected.TestObject("value5", persistentListOf(1, 2, 3)),
                        Expected.TestObject("value6", persistentListOf(1, 2, 3))
                    ),
                    map = persistentMapOf(
                        "1" to Expected.TestObject("value7", persistentListOf(1, 2, 3)),
                        "2" to Expected.TestObject("value8", persistentListOf(1, 2, 3))
                    )
                )
            )
        )
    }

    private data class Expected(
        @SerializedName("short")
        val short: TypedContent<Short>,
        @SerializedName("integer")
        val integer: TypedContent<Int>,
        @SerializedName("long")
        val long: TypedContent<Long>,
        @SerializedName("float")
        val float: TypedContent<Float>,
        @SerializedName("double")
        val double: TypedContent<Double>,
        @SerializedName("char")
        val char: TypedContent<Char>,
        @SerializedName("string")
        val string: TypedContent<String>,
        @SerializedName("object")
        val objects: TypedContent<TestObject>
    ) {
        data class TypedContent<T>(
            @SerializedName("immutable")
            val immutable: Immutable<T>,
            @SerializedName("persistent")
            val persistent: Persistent<T>
        )

        data class Immutable<T>(
            @SerializedName("list")
            val list: ImmutableList<T>,
            @SerializedName("collection")
            val collection: ImmutableCollection<T>,
            @SerializedName("set")
            val set: ImmutableSet<T>,
            @SerializedName("map")
            val map: ImmutableMap<String, T>
        )

        data class Persistent<T>(
            @SerializedName("list")
            val list: PersistentList<T>,
            @SerializedName("collection")
            val collection: PersistentCollection<T>,
            @SerializedName("set")
            val set: PersistentSet<T>,
            @SerializedName("map")
            val map: PersistentMap<String, T>
        )

        data class TestObject(
            @SerializedName("string")
            val string: String,
            @SerializedName("numbers")
            val numbers: ImmutableList<Int>
        )
    }
}
