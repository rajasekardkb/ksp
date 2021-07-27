/*
 * Copyright (c) 2016 riontech-xten
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ksp.kspm.core.data

import timber.log.Timber
import java.util.*

/**
 * Created by Vaghela Mithun R. on 14/03/21.
 * vaghela.mithun@gmail.com
 */
object DataWrapper {
    private val TAG = DataWrapper::class.qualifiedName
    private var mapWrapper: HashMap<String, MutableList<*>> = HashMap()

    fun <T> getList(key: String): ArrayList<T>? {
        return if (mapWrapper.containsKey(key)) {
            mapWrapper.get(key) as ArrayList<T>
        } else {
            ArrayList<T>()
        }
    }

    fun <T> add(key: String, item: T) {
        val list: ArrayList<T>
        if (hasKeyList(key)) {
            list = mapWrapper.get(key) as ArrayList<T>
        } else {
            list = ArrayList()
        }
        list.add(item)
        mapWrapper.put(key, list)
    }

    fun <T> addList(key: String, list: MutableList<T>?) {
        mapWrapper.put(key, list!!)
    }

    fun removeList(key: String) {
        if (hasKeyList(key)) {
            mapWrapper.remove(key)
        } else {
            Timber.d(TAG, "No List exists in wrapper with name of $key")
        }
    }

    private fun hasKeyList(key: String): Boolean {
        return mapWrapper.containsKey(key)
    }

    fun saveMap(map: HashMap<String, MutableList<*>>){
        mapWrapper = map
    }
}