package com.prismosis.dpanimations

import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.*
import android.widget.*
import androidx.cardview.widget.CardView


/**
 * Created by Ehsan Saddique on 2020-03-17
 */

class MainFragment : Fragment() {

    lateinit var item: DTOItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!arguments!!.containsKey(ITEM))
            throw RuntimeException("Fragment must contain a \"$ITEM\" argument!")
        item = arguments!!.getParcelable(ITEM)!!

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = activity?.layoutInflater?.inflate(R.layout.fragment_layout_1, container, false)

        view?.tag = item.page

        view?.findViewById<TextView>(R.id.title)?.text = item.title
        view?.findViewById<TextView>(R.id.price)?.text = item.price
        val card = view?.findViewById<CardView>(R.id.card_view)
        val cardRelative = card?.findViewById<RelativeLayout>(R.id.card_relative)
        val detailView = view?.findViewById<RelativeLayout>(R.id.detail_view)
        val image = view?.findViewById<ImageView>(R.id.display_image)
        val buy = cardRelative?.findViewById<Button>(R.id.buy)
        val buyDetail = view?.findViewById<Button>(R.id.buy_detail)
        image?.setImageResource(item.image)

        buy?.setOnClickListener {
            cardRelative.removeView(buy)
            animateView(card, image!!, buy, detailView!!, buyDetail!!)
        }

        return view
    }

    fun animateView(cardView: CardView, imageView: ImageView, buyButton: Button, detailView: RelativeLayout, buyDetail: Button) {
        val animationSet = AnimationSet(true)

//        val a = TranslateAnimation(
//            Animation.ABSOLUTE, 0f, Animation.ABSOLUTE, 200f,
//            Animation.ABSOLUTE, 0f, Animation.ABSOLUTE, 800f
//        )
//        a.duration = 1000
//
//        val rect = Rect()
//        view.getLocalVisibleRect(rect)
//        val bottomimagevalue: Float = rect.width().toFloat()
//        val rightimagevalue = rect.height().toFloat()
//        val r = RotateAnimation(0f, 180f, bottomimagevalue, rightimagevalue)
//        r.fillAfter = true
////        r.startOffset = 1000
//        r.duration = 1000
//
//        animationSet.addAnimation(a)
//        animationSet.addAnimation(r)
//
//        view.startAnimation(animationSet)

        val animImage = AnimationUtils.loadAnimation(activity, R.anim.image_anim)
        val animCard = AnimationUtils.loadAnimation(activity, R.anim.card_anim)
        val animBuyDetail = AnimationUtils.loadAnimation(activity, R.anim.buy_anim)

        detailView.visibility = View.VISIBLE

        imageView.startAnimation(animImage)
        cardView.startAnimation(animCard)
        buyDetail.startAnimation(animBuyDetail)

        animImage.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
                //
            }

            override fun onAnimationEnd(animation: Animation?) {
                val bounceAnim = AnimationUtils.loadAnimation(activity, R.anim.image_anim_bounce)
                imageView.startAnimation(bounceAnim)
            }

            override fun onAnimationStart(animation: Animation?) {
                //
            }

        })
    }

    companion object {

        private val ITEM = "item"

        fun newInstance(item: DTOItem): MainFragment {
            val frag = MainFragment()
            val b = Bundle()
            b.putParcelable(ITEM, item)
            frag.setArguments(b)
            return frag
        }
    }

}