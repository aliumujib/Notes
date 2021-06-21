package com.task.noteapp.navigator

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.task.noteapp.R
import com.task.noteapp.sharedlib.navigator.Navigator
import javax.inject.Provider

class NavigatorImpl(private val navController: Provider<NavController>) : Navigator {

    override fun goToNoteEditor(noteId: Int?) {
        if(noteId != null){
            navController.get().navigate(
                R.id.action_navigation_notes_list_to_navigation_notes_editor,
                bundleOf("noteId" to noteId)
            )
        }else{
            navController.get().navigate(
                R.id.action_navigation_notes_list_to_navigation_notes_editor
            )
        }
    }

    override fun goBack() {
        navController.get().popBackStack()
    }


}