package com.example.androidtechchallenge.custom

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import androidx.annotation.Nullable


class CustomTextInputLayout(context: Context, attrs: AttributeSet) : TextInputLayout(context, attrs) {


    override fun setError(@Nullable error: CharSequence?) {
        super.setError(error)
        try {
            val et = editText
            val editTextBackground = et!!.background
            editTextBackground.clearColorFilter()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        try {
            val et = editText
            val editTextBackground = et!!.background
            editTextBackground.clearColorFilter()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
