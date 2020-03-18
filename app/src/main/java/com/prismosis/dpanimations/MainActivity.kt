package com.prismosis.dpanimations

import android.animation.ArgbEvaluator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import android.view.WindowManager
import android.os.Build
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE







class MainActivity : AppCompatActivity() {

    private var mViewPager: ViewPager? = null
    var argbEvaluator = ArgbEvaluator()
    var color1 = 0
    var color2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_layout)
        supportActionBar?.hide()

        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        color1 = resources.getColor(R.color.colorBackground1, null)
        color2 = resources.getColor(R.color.colorBackground2, null)

        mViewPager = findViewById(R.id.viewpager)
        mViewPager?.adapter = ViewPagerAdapter(supportFragmentManager)
        mViewPager!!.setPageTransformer(false, ParallaxPagerTransformer())


        mViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                if (position == 0) {

                    mViewPager?.setBackgroundColor(
                        argbEvaluator.evaluate(
                            positionOffset,
                            color1,
                            color2
                        ) as Int
                    )

                } else {

                    // the last page color
                    mViewPager?.setBackgroundColor(color2)

                }

            }
            override fun onPageSelected(position: Int) {

            }

        })
    }
}
