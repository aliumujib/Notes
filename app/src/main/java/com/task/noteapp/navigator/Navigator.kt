package com.task.noteapp.navigator

import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import com.task.noteapp.R
import com.task.noteapp.sharedlib.navigator.Navigator
import javax.inject.Provider

class NavigatorImpl(private val navController: Provider<NavController>) : Navigator {

    override fun goToNoteEditor(noteId: Int, sharedView: View?) {
        val extras = FragmentNavigator.Extras.Builder()
        sharedView?.let {
            extras.addSharedElement(sharedView, "shared_element$noteId")
        }
        navController.get().navigate(
            R.id.action_navigation_notes_list_to_navigation_notes_editor,
            bundleOf("noteId" to noteId),
            null,
            extras.build()
        )
    }

    override fun goBack() {
        navController.get().popBackStack()
    }
}
