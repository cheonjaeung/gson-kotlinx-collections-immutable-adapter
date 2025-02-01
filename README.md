# Gson Kotlin Immutable Collections Type Adapter Library

[![CI](https://github.com/cheonjaeung/gson-kotlinx-collections-immutable-adapter/actions/workflows/ci.yml/badge.svg)](https://github.com/cheonjaeung/gson-kotlinx-collections-immutable-adapter/actions/workflows/ci.yml)
![maven-central](https://img.shields.io/maven-central/v/com.cheonjaeung.gson/kotlinx-collections-immutable-adapter)
[![Static Badge](https://img.shields.io/badge/License-Apache%202.0-Green)](https://github.com/cheonjaeung/gridlayout-compose/blob/main/LICENSE.txt)

A [Gson](https://github.com/google/gson) `TypeAdapterFactory` for collection types of [kotlinx.collections.immutable](https://github.com/Kotlin/kotlinx.collections.immutable).

## Installation

This library is published to Maven Central repository.

```kotlin
dependencies {
    implementation("com.cheonjaeung.gson:kotlinx-collections-immutable-adapter:1.0.0")
}
```

### Dependencies

This library is built on these dependencies:

- Java 8
- Kotlin 1.9.21
- Gson 2.12.1
- Kotlinx Collections Immutable: 0.3.8

## Usage

Register `ImmutableCollectionTypeAdapterFactory` to `GsonBuilder` when you build `Gson` instance.

```kotlin
val builder = GsonBuilder()
builder.registerTypeAdapterFactory(ImmutableCollectionTypeAdapterFactory())
// ...
val gson = builder.create()
```

Now the `Gson` instance can deserialize Kotlin immutable collection types.

```kotlin
val jsonString = """
{
    "numbers": [1, 2, 3, 4, 5]
}
"""

data class Content(
    @SerializedName("numbers")
    val numbers: ImmutableList<Int>
)

val deserialized = gson.fromJson(jsonString, Content::class.java)

println(deserialized.numbers) // [1, 2, 3, 4, 5]
```

`ImmutableCollectionTypeAdapterFactory` supports following types:

- ImmutableCollection
- ImmutableList
- ImmutableSet
- ImmutableMap
- PersistentCollection
- PersistentList
- PersistentSet
- PersistentMap

> [!NOTE]
> Gson can serialize immutable collections to JSON string without `ImmutableCollectionTypeAdapterFactory`.

### Complex Map Key

Basically, Gson serializes map key using `toString()`.

```kotlin
data class Person(
    val firstName: String,
    val lastName: String
)

val gson = GsonBuilder().create()

val map = mapOf(
    Person("John", "Doe") to 32,
    Person("Jane", "Doe") to 30
)

println(gson.toJson(map))
// {
//     "Person(firstName=John, lastName=Doe)": 32,
//     "Person(firstName=Jane, lastName=Doe)": 30
// }
```

Gson supports complex types of map key.
If `complexMapKeySerialization` is enabled, Gson serializes complex map to JSON array of key value pairs.
`ImmutableCollectionTypeAdapterFactory` also supports it.
If you enable it with `GsonBuilder.enableComplexMapKeySerialization()`, you should set factory's `complexMapKeySerialization` to `true`.

```kotlin
data class Person(
    val firstName: String,
    val lastName: String
)

val gson = GsonBuilder()
    .enableComplexMapKeySerialization()
    .registerTypeAdapterFactory(ImmutableCollectionTypeAdapterFactory(
        complexMapKeySerialization = true
    ))
    .create()

val map = persistentMapOf(
    Person("John", "Doe") to 32,
    Person("Jane", "Doe") to 30
)

println(gson.toJson(map))
// [
//     [
//         {"firstName":"John","lastName":"Doe"},
//         32
//     ],
//     [
//         {"firstName":"Jane","lastName":"Doe"},
//         30
//     ]
// ]
```

Even if `complexMapKeySerialization` is enabled, simple map is serialized as a regular JSON object.

```kotlin
val gson = GsonBuilder()
    .enableComplexMapKeySerialization()
    .registerTypeAdapterFactory(ImmutableCollectionTypeAdapterFactory(
        complexMapKeySerialization = true
    ))
    .create()

val simpleMap = persistentMapOf("a" to 1, "b" to 2)

println(gson.toJson(simpleMap)) // {"a": 1,"b": 2}
```

## License

This project is licensed under the Apache License 2.0.

```
Copyright 2025 Cheon Jaeung

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
