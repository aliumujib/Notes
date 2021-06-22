package com.task.noteapp.sharedlib.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import com.task.noteapp.sharedlib.R
import com.task.noteapp.sharedlib.databinding.EmptyErrorViewBinding

class LoadingEmptyErrorStateView : LinearLayout {

    private var view: View
    private var _binding: EmptyErrorViewBinding
    private val binding get() = _binding
    private var errorIcon: Drawable? = null
    private var emptyIcon: Drawable? = null
    private var errorTitle: String? = null
    private var emptyTitle: String? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        _binding = EmptyErrorViewBinding.inflate(inflater, this, true)
        view = binding.root

        attrs?.let {
            with(context.obtainStyledAttributes(attrs, R.styleable.LoadingEmptyErrorStateView)) {
                errorIcon = getDrawable(R.styleable.LoadingEmptyErrorStateView_error_icon)
                emptyIcon = getDrawable(R.styleable.LoadingEmptyErrorStateView_empty_icon)

                val emptyTextId = getResourceId(
                    R.styleable.LoadingEmptyErrorStateView_empty_text,
                    View.NO_ID
                )
                val errorTextId = getResourceId(
                    R.styleable.LoadingEmptyErrorStateView_error_text,
                    View.NO_ID
                )
                emptyTitle = if (emptyTextId == View.NO_ID) {
                    getString(
                        R.styleable.LoadingEmptyErrorStateView_empty_text
                    )
                } else {
                    resources.getString(emptyTextId)
                }

                errorTitle = if (errorTextId == View.NO_ID) {
                    getString(
                        R.styleable.LoadingEmptyErrorStateView_error_text
                    )
                } else {
                    resources.getString(errorTextId)
                }
                recycle()
            }
        }

        setEmpty()
    }

    fun setIcon(@DrawableRes iconRes: Int) {
        binding.icon.setBackgroundResource(iconRes)
    }

    fun setErrorText(@StringRes stringRes: Int) {
        binding.text.setText(stringRes)
    }

    fun setErrorText(string: String) {
        binding.text.text = string
    }

    fun setError() {
        binding.icon.setImageDrawable(errorIcon)
        binding.text.text = errorTitle
        binding.icon.isVisible = true
        binding.text.isVisible = true
    }

    fun setEmpty() {
        binding.icon.setImageDrawable(emptyIcon)
        binding.text.text = emptyTitle
        binding.icon.isVisible = true
        binding.text.isVisible = true
    }
}
