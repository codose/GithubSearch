package com.android.githubsearch.ui

import android.support.test.espresso.IdlingResource
import com.android.githubsearch.ui.AndroidXIdlingResource.SupportResourceCallback

class AndroidXIdlingResource private constructor(private val mDelegate: IdlingResource) : androidx.test.espresso.IdlingResource {
    override fun getName(): String {
        return mDelegate.name
    }

    override fun isIdleNow(): Boolean {
        return mDelegate.isIdleNow
    }

    override fun registerIdleTransitionCallback(callback: androidx.test.espresso.IdlingResource.ResourceCallback) {
        mDelegate.registerIdleTransitionCallback(SupportResourceCallback(callback))
    }

    inner class SupportResourceCallback internal constructor(private val mDelegate: androidx.test.espresso.IdlingResource.ResourceCallback) :
        IdlingResource.ResourceCallback {
        override fun onTransitionToIdle() {
            mDelegate.onTransitionToIdle()
        }
    }

    companion object {
        fun asAndroidX(supportIdlingResource: IdlingResource): AndroidXIdlingResource {
            return AndroidXIdlingResource(supportIdlingResource)
        }
    }
}
