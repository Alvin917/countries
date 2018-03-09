package com.patloew.countries.injection.components

import android.content.Context
import android.content.res.Resources
import com.patloew.countries.data.local.CountryRepo
import com.patloew.countries.data.local.PrefRepo
import com.patloew.countries.data.remote.CountryApi
import com.patloew.countries.injection.modules.AppModule
import com.patloew.countries.injection.modules.DataModule
import com.patloew.countries.injection.modules.NetModule
import com.patloew.countries.injection.qualifier.AppContext
import com.patloew.countries.injection.scopes.PerApplication
import com.patloew.countries.ui.base.feedback.Toaster
import com.squareup.leakcanary.RefWatcher
import dagger.Component
import io.realm.Realm


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
 * limitations under the License.
 *
 * ------
 *
 * FILE MODIFIED 2017 Tailored Media GmbH */
@PerApplication
@Component(modules = arrayOf(AppModule::class, NetModule::class, DataModule::class))
interface AppComponent : AppComponentProvides {

}

interface AppComponentProvides {
    @AppContext fun appContext(): Context
    fun resources(): Resources
    fun refWatcher(): RefWatcher

    fun realm(): Realm
    fun countryRepo(): CountryRepo
    fun prefRepo(): PrefRepo
    fun countryApi(): CountryApi

    fun toaster(): Toaster
}