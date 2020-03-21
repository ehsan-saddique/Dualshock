package com.prismosis.dpanimations

import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.*
import android.widget.*
import androidx.cardview.widget.CardView


/**
 * Created by Ehsan Saddique on 2020-03-17
 */

class MainFragment : Fragment() {

    var backgroundColor = R.color.colorBackground1

    lateinit var item: DTOItem
    private var isDetailView = false

    lateinit var card: CardView
    lateinit var cardRelative : RelativeLayout
    lateinit var detailView : RelativeLayout
    lateinit var title : TextView
    lateinit var price : TextView
    lateinit var name : TextView
    lateinit var mainImage : ImageView
    lateinit var buy : Button

    lateinit var buyDetail : Button
    lateinit var bottomImage : ImageView
    lateinit var titleDetail1 : TextView
    lateinit var titleDetail2 : TextView
    lateinit var nameDetail : TextView
    lateinit var categoryDetail : TextView
    lateinit var descriptionDetail : TextView
    lateinit var priceDetail : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!arguments!!.containsKey(ITEM))
            throw RuntimeException("Fragment must contain a \"$ITEM\" argument!")
        item = arguments!!.getParcelable(ITEM)!!

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = activity?.layoutInflater?.inflate(R.layout.fragment_layout_1, container, false)!!

        view.tag = item.page

        card = view.findViewById(R.id.card_view)
        cardRelative = card.findViewById(R.id.card_relative)
        detailView = view.findViewById(R.id.detail_view)
        title = view.findViewById(R.id.title)
        price = view.findViewById(R.id.price)
        name = view.findViewById(R.id.name)
        mainImage = view.findViewById(R.id.display_image)
        buy = cardRelative.findViewById(R.id.buy)
        buyDetail = view.findViewById(R.id.buy_detail)
        bottomImage = view.findViewById(R.id.bottom_image)
        mainImage.setImageResource(item.image)

        buyDetail = view.findViewById(R.id.buy_detail)
        bottomImage = view.findViewById(R.id.bottom_image)
        titleDetail1 = detailView.findViewById(R.id.title_detail_1)
        titleDetail2 = detailView.findViewById(R.id.title_detail_2)
        nameDetail = detailView.findViewById(R.id.name_detail)
        categoryDetail = detailView.findViewById(R.id.category_detail)
        descriptionDetail = detailView.findViewById(R.id.description_detail)
        priceDetail = detailView.findViewById(R.id.price_detail)

        title.text = item.title
        price.text = item.price
        titleDetail1.setTextColor(resources.getColor(item.titleColor, null))
        titleDetail2.setTextColor(resources.getColor(item.titleColor, null))

        val splitStrings = item.title.split(" ")
        if (splitStrings.size >= 2) {
            titleDetail1.text = splitStrings[0]
            titleDetail2.text = splitStrings[1]
        }
        else {
            titleDetail1.text = item.title
            titleDetail2.text = ""
        }

        backgroundColor = item.backgroudColor

        buy.setOnClickListener {

            showDetail()
        }

        return view
    }

    private fun showDetail() {
        isDetailView = true
        cardRelative.removeView(buy)
        cardRelative.removeView(title)
        cardRelative.removeView(price)
        cardRelative.removeView(name)


        val animImage = AnimationUtils.loadAnimation(activity, R.anim.image_anim)
        val animImageBounce = AnimationUtils.loadAnimation(activity, R.anim.image_anim_bounce)
        val animCard = AnimationUtils.loadAnimation(activity, R.anim.card_anim)
        val animBuyDetail = AnimationUtils.loadAnimation(activity, R.anim.buy_anim)
        val animPriceDetail = AnimationUtils.loadAnimation(activity, R.anim.detail_price_anim)
        val animTitleDetail1 = AnimationUtils.loadAnimation(activity, R.anim.detail_title_anim)
        val animTitleDetail2 = AnimationUtils.loadAnimation(activity, R.anim.detail_title_anim)
        val animTitleDetailBounce = AnimationUtils.loadAnimation(activity, R.anim.title_detail_anim_bounce)
        val animBottomImage = AnimationUtils.loadAnimation(activity, R.anim.bottom_image_anim)


        detailView.visibility = View.VISIBLE
        nameDetail.alpha = 0f
        categoryDetail.alpha = 0f
        descriptionDetail.alpha = 0f

        mainImage.startAnimation(animImage)
        card.startAnimation(animCard)
        buyDetail.startAnimation(animBuyDetail)
        priceDetail.startAnimation(animPriceDetail)

        animTitleDetail2.startOffset = 100
        titleDetail1.startAnimation(animTitleDetail1)
        titleDetail2.startAnimation(animTitleDetail2)

        animImage.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                bottomImage.visibility = View.VISIBLE
                mainImage.startAnimation(animImageBounce)
                bottomImage.startAnimation(animBottomImage)
                nameDetail.animate().alpha(1f).duration = 200
                categoryDetail.animate().setStartDelay(100).alpha(1f).duration = 200
                descriptionDetail.animate().setStartDelay(100).alpha(1f).duration = 200
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

    fun onBackPressed() {
        if (!isDetailView) {
            return
        }

        val animImageBack = AnimationUtils.loadAnimation(activity, R.anim.image_anim_back)
        val animCardBack = AnimationUtils.loadAnimation(activity, R.anim.card_back_anim)
        val animBuyDetailBack = AnimationUtils.loadAnimation(activity, R.anim.buy_back_anim)

        titleDetail1.clearAnimation()
        titleDetail2.clearAnimation()

        mainImage.startAnimation(animImageBack)
        card.startAnimation(animCardBack)
        buyDetail.startAnimation(animBuyDetailBack)
        titleDetail1.visibility = View.INVISIBLE
        titleDetail2.visibility = View.INVISIBLE
        bottomImage.animate().setDuration(200).alpha(0f).start()
        nameDetail.visibility = View.INVISIBLE
        categoryDetail.visibility = View.INVISIBLE
        descriptionDetail.visibility = View.INVISIBLE
        priceDetail.animate().setDuration(200).alpha(0f).start()

        isDetailView = false
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