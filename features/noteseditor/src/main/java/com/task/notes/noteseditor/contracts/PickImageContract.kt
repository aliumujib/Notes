package com.task.notes.noteseditor.contracts

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import com.unsplash.pickerandroid.photopicker.presentation.UnsplashPickerActivity

class PickImageContract : ActivityResultContract<Boolean, UnsplashPhoto?>() {

    private lateinit var context: Context

    override fun createIntent(context: Context, isMultiSelect: Boolean): Intent {
        this.context = context
        return UnsplashPickerActivity.getStartingIntent(
            context, // context
            isMultiSelect
        )
    }

    override fun parseResult(resultCode: Int, intent: Intent?): UnsplashPhoto? {
        return if (resultCode == Activity.RESULT_OK) {
            val photos: ArrayList<UnsplashPhoto> =
                intent?.getParcelableArrayListExtra(UnsplashPickerActivity.EXTRA_PHOTOS)!!
            photos[0]
        } else {
            null
        }
    }

}