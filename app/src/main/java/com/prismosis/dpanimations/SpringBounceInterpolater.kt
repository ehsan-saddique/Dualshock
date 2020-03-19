package com.prismosis.dpanimations

import android.view.animation.Interpolator
import kotlin.math.abs
import kotlin.math.cos

/**
 * Created by Ehsan Saddique on 2020-03-19
 */

class SpringBounceInterpolator(val bounces: Int, val energy: Double) : Interpolator {
    override fun getInterpolation(x: Float): Float = (1.0 + (-abs(cos(x * 10 * bounces / Math.PI)) * getCurveAdjustment(x))).toFloat()

    private fun getCurveAdjustment(x: Float) : Double = -(2 * (1 - x) * x * energy + x * x) + 1
}

class MyBounceInterpolator
/**
 * Initialize a new interpolator.
 *
 * @param      amplitude   The amplitude of the bounces. The higher value produces more pronounced bounces. The lower values (0.1, for example) produce less noticeable wobbles.
 * @param      frequency   The frequency of the bounces. The higher value produces more wobbles during the animation time period.
 */
    (amplitude: Double, frequency: Double) : android.view.animation.Interpolator {
    /**
     * The amplitude of the bounces. The higher value (10, for example) produces more pronounced bounces.
     * The lower values (0.1, for example) produce less noticeable wobbles.
     */
    internal var mAmplitude = 1.0

    /**
     * The frequency of the bounces. The higher value produces more wobbles during the animation time period.
     */
    internal var mFrequency = 10.0

    init {
        mAmplitude = amplitude
        mFrequency = frequency
    }

    override fun getInterpolation(time: Float): Float {
        var amplitude = mAmplitude
        if (amplitude == 0.0) {
            amplitude = 0.05
        }

        // The interpolation curve equation:
        //    -e^(-time / amplitude) * cos(frequency * time) + 1
        //
        // View the graph live: https://www.desmos.com/calculator/6gbvrm5i0s
        return (-1.0 * Math.pow(Math.E, -time / mAmplitude) * Math.cos(mFrequency * time) + 1).toFloat()
    }
}