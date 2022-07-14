package com.n11.n11testcase.ui.common.bindingadapters

import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.target.ViewTarget
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.Q])
class ImageViewBindingAdaptersTest {

    private lateinit var activity: AppCompatActivity

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(AppCompatActivity::class.java).create().get()
        mockkObject(ImageViewBindingAdapters)
    }

    @Test
    fun setImageUrl_VerifyImageLoad_SetImageUrlIsNotNull() {
        val imageUrl =
            "https://is3-ssl.mzstatic.com/image/thumb/Video127/v4/d3/31/eb/d331eb87-c574-c916-9a1c-3d4333476e19/source/100x100bb.jpg"
        val imageView = mockkClass(ImageView::class, relaxed = true)
        val requestManager = mockk<RequestManager>()
        val requestBuilder = mockk<RequestBuilder<Drawable>>()
        val viewTarget = mockk<ViewTarget<ImageView, Drawable>>()
        mockkStatic(Glide::class)
        every { Glide.with(imageView.context) } returns requestManager
        every { requestManager.load(imageUrl) } returns requestBuilder
        every { requestBuilder.into(imageView) } returns viewTarget
        ImageViewBindingAdapters.setImageUrl(imageView, imageUrl)
        verifySequence {
            Glide.with(imageView.context)
            requestManager.load(imageUrl)
            requestBuilder.into(imageView)
        }
    }

    @Test
    fun setImageUrl_VerifyNotImageLoad_SetImageUrlIsNull() {
        val imageUrl: String? = null
        val imageView = mockkClass(ImageView::class, relaxed = true)
        val requestManager = mockk<RequestManager>()
        mockkStatic(Glide::class)
        every { Glide.with(imageView.context) } returns requestManager
        ImageViewBindingAdapters.setImageUrl(imageView, imageUrl)
        verify(exactly = 0) {
            Glide.with(imageView.context)
        }
    }

}