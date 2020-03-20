package com.prismosis.dpanimations

import android.animation.ArgbEvaluator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import android.view.WindowManager
import android.os.Build
import android.os.Handler
import android.view.View


class MainActivity : AppCompatActivity() {

    private var mViewPager: ViewPager? = null
    private var mViewPagerAdapter: ViewPagerAdapter? = null
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
        mViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        mViewPager?.adapter = mViewPagerAdapter
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

    override fun onBackPressed() {
        val manager = this.supportFragmentManager

        val fragment = mViewPagerAdapter!!.getCurrentFragment() as MainFragment
        fragment.onBackPressed()
        Handler().postDelayed({
            manager.beginTransaction().detach(fragment).attach(fragment).commit()
        }, 400)
    }
}
