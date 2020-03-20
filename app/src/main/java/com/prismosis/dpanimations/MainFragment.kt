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
        val title = view?.findViewById<TextView>(R.id.title)
        val price = view?.findViewById<TextView>(R.id.price)
        val name = view?.findViewById<TextView>(R.id.name)
        val image = view?.findViewById<ImageView>(R.id.display_image)
        val buy = cardRelative?.findViewById<Button>(R.id.buy)
        val buyDetail = view?.findViewById<Button>(R.id.buy_detail)
        val bottomImage = view?.findViewById<ImageView>(R.id.bottom_image)
        image?.setImageResource(item.image)

        buy?.setOnClickListener {
            cardRelative.removeView(buy)
            cardRelative.removeView(title)
            cardRelative.removeView(price)
            cardRelative.removeView(name)
            animateView(card, image!!, buy, detailView!!, buyDetail!!, bottomImage!!)
        }

        return view
    }

    fun animateView(cardView: CardView, imageView: ImageView, buyButton: Button,
                    detailView: RelativeLayout, buyDetail: Button, bottomImage: ImageView) {
        val animationSet = AnimationSet(true)


        val animImage = AnimationUtils.loadAnimation(activity, R.anim.image_anim)
        val animImageBounce = AnimationUtils.loadAnimation(activity, R.anim.image_anim_bounce)
        val animCard = AnimationUtils.loadAnimation(activity, R.anim.card_anim)
        val animBuyDetail = AnimationUtils.loadAnimation(activity, R.anim.buy_anim)
        val animPriceDetail = AnimationUtils.loadAnimation(activity, R.anim.detail_price_anim)
        val animTitleDetail1 = AnimationUtils.loadAnimation(activity, R.anim.detail_title_anim)
        val animTitleDetail2 = AnimationUtils.loadAnimation(activity, R.anim.detail_title_anim)
        val animTitleDetailBounce = AnimationUtils.loadAnimation(activity, R.anim.title_detail_anim_bounce)
        val animBottomImage = AnimationUtils.loadAnimation(activity, R.anim.bottom_image_anim)

        val nameDetail = detailView.findViewById<TextView>(R.id.name_detail)
        val categoryDetail = detailView.findViewById<TextView>(R.id.category_detail)
        val descriptionDetail = detailView.findViewById<TextView>(R.id.description_detail)
        val priceDetail = detailView.findViewById<TextView>(R.id.price_detail)
        val titleDetail1 = detailView.findViewById<TextView>(R.id.title_detail_1)
        val titleDetail2 = detailView.findViewById<TextView>(R.id.title_detail_2)


        detailView.visibility = View.VISIBLE
        nameDetail.alpha = 0f
        categoryDetail.alpha = 0f
        descriptionDetail.alpha = 0f

        imageView.startAnimation(animImage)
        cardView.startAnimation(animCard)
        buyDetail.startAnimation(animBuyDetail)
        priceDetail.startAnimation(animPriceDetail)

        animTitleDetail2.startOffset = 100
        titleDetail1.startAnimation(animTitleDetail1)
        titleDetail2.startAnimation(animTitleDetail2)

        animImage.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                bottomImage.visibility = View.VISIBLE
                imageView.startAnimation(animImageBounce)
                bottomImage.startAnimation(animBottomImage)
                nameDetail.animate().alpha(1f).duration = 800
                categoryDetail.animate().setStartDelay(100).alpha(1f).duration = 800
                descriptionDetail.animate().setStartDelay(300).alpha(1f).duration = 800
            }

            override fun onAnimationStart(animation: Animation?) {}

        })

        animTitleDetail1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                titleDetail1.startAnimation(animTitleDetailBounce)
            }

            override fun onAnimationStart(animation: Animation?) {}

        })

        animTitleDetail2.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                titleDetail2.startAnimation(animTitleDetailBounce)
            }

            override fun onAnimationStart(animation: Animation?) {}

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