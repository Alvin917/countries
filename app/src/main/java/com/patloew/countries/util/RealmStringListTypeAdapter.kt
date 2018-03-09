package com.patloew.countries.util

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import io.realm.RealmList
import java.io.IOException

/* Copyright 2016 Patrick Löwenstein
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */
class RealmStringListTypeAdapter private constructor() : TypeAdapter<RealmList<String>>() {

    companion object {
        val INSTANCE = RealmStringListTypeAdapter().nullSafe()
    }

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, src: RealmList<String>) {
        writer.beginArray()
        for (realmString in src) { writer.value(realmString) }
        writer.endArray()
    }

    @Throws(IOException::class)
    override fun read(reader: JsonReader): RealmList<String> {
        val realmStrings = RealmList<String>()

        reader.beginArray()

        while (reader.hasNext()) {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull()
            } else {
                realmStrings.add(reader.nextString())
            }
        }

        reader.endArray()

        return realmStrings
    }
}
