package com.task.noteapp.di

import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.task.noteapp.MainActivity
import com.task.noteapp.R
import com.task.noteapp.navigator.NavigatorImpl
import com.task.noteapp.sharedlib.navigator.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object NavigatorModule {

    @Provides
    @ActivityScoped
    fun providesNavigator(activity: FragmentActivity): Navigator {
        return NavigatorImpl { (activity as MainActivity).findNavController(R.id.nav_host_fragment_container) }
    }

}
