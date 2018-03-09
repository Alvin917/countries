package com.patloew.countries

import android.app.Application
import android.content.res.Resources
import com.patloew.countries.injection.components.AppComponent
import com.patloew.countries.injection.components.DaggerAppComponent
import com.patloew.countries.injection.modules.AppModule
import com.patloew.countries.util.*
import com.squareup.leakcanary.LeakCanary
import io.reactivex.plugins.RxJavaPlugins
import io.realm.Realm
import io.realm.RealmConfiguration
import paperparcel.Adapter
import paperparcel.ProcessorConfig
import timber.log.Timber

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

@ProcessorConfig(
        adapters = arrayOf(
            Adapter(RealmListPaperParcelTypeConverter::class),
            Adapter(ObservableFieldPaperParcelTypeConverter::class),
            Adapter(ObservableBooleanPaperParcelTypeConverter::class),
            Adapter(ObservableDoublePaperParcelTypeConverter::class),
            Adapter(ObservableFloatPaperParcelTypeConverter::class),
            Adapter(ObservableIntPaperParcelTypeConverter::class),
            Adapter(ObservableLongPaperParcelTypeConverter::class)
        )
)
class CountriesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) return

        Timber.plant(Timber.DebugTree())

        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().build())

        instance = this
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        RxJavaPlugins.setErrorHandler({ Timber.e(it) })
    }

    companion object {

        lateinit var instance: CountriesApp
            private set

        lateinit var appComponent: AppComponent
            private set

        val realm: Realm
            get() = appComponent.realm()

        val res: Resources
            get() = instance.resources
    }
}
