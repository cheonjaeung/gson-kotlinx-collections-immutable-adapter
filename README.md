# Gson Kotlin Immutable Collections Type Adapter Library

[![CI](https://github.com/cheonjaeung/gson-kotlinx-collections-immutable-adapter/actions/workflows/ci.yml/badge.svg)](https://github.com/cheonjaeung/gson-kotlinx-collections-immutable-adapter/actions/workflows/ci.yml)
[![Static Badge](https://img.shields.io/badge/License-Apache%202.0-Green)](https://github.com/cheonjaeung/gridlayout-compose/blob/main/LICENSE.txt)

A [Gson](https://github.com/google/gson) `TypeAdapterFactory` for collection types of [kotlinx.collections.immutable](https://github.com/Kotlin/kotlinx.collections.immutable).

## Getting Started

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

## Installation

```kotlin
dependencies {
    implementation("com.cheonjaeung.gson:kotlinx-collections-immutable-adapter:<version>")
}
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
