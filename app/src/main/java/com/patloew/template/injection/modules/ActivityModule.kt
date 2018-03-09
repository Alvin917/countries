package com.patloew.template.injection.modules

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

import com.patloew.template.injection.qualifier.ActivityContext
import com.patloew.template.injection.qualifier.ActivityFragmentManager
import com.patloew.template.injection.scopes.PerActivity
import com.patloew.template.ui.base.feedback.ActivitySnacker
import com.patloew.template.ui.base.feedback.Snacker
import com.patloew.template.ui.base.navigator.ActivityNavigator
import com.patloew.template.ui.base.navigator.Navigator

import dagger.Module
import dagger.Provides

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
@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @PerActivity
    @ActivityContext
    internal fun provideActivityContext(): Context = activity

    @Provides
    @PerActivity
    @ActivityFragmentManager
    internal fun provideFragmentManager(): FragmentManager = activity.supportFragmentManager

    @Provides
    @PerActivity
    internal fun provideNavigator(): Navigator = ActivityNavigator(activity)

    @Provides
    @PerActivity
    internal fun provideSnacker(): Snacker = ActivitySnacker(activity)
}
