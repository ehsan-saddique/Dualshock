package com.prismosis.dpanimations

import android.view.View
import androidx.viewpager.widget.ViewPager



/**
 * Created by Ehsan Saddique on 2020-03-18
 */

class ParallaxPagerTransformer() : ViewPager.PageTransformer {
    private var speed = 0.3f

    override fun transformPage(page: View, position: Float) {

        val displayImage = page.findViewById<View>(R.id.display_image)
        val price = page.findViewById<View>(R.id.price)

        val pagePosition = page.tag as Int

        if (position > -1 && position < 1) {
            if (pagePosition == 0) {
                if (displayImage != null) {
                    val displayImageWidth = displayImage.width
                    val imageTranslation = position * displayImageWidth * 0.3f
                    displayImage.translationX = imageTranslation
                }
                if (price != null) {
                    val priceWidth = price.width
                    val priceTranslation = position * priceWidth * 0.5f
                    price.translationX = priceTranslation
                }
            }
            else {
                if (displayImage != null) {
                    val displayImageWidth = displayImage.width
                    val imageTranslation = position * displayImageWidth
                    displayImage.translationX = imageTranslation
                }
                if (price != null) {
                    val priceWidth = price.width
                    val priceTranslation = position * priceWidth * 0.3f
                    price.translationX = priceTranslation
                }
            }
        }
    }
}