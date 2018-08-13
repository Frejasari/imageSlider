package com.example.sari.imageslider

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso

class ImageSlider : LinearLayout {

    private val adapter = ImageAdapter()
    private val imagePager by lazy { findViewById<ViewPager>(R.id.imagePager) }
    private val countText by lazy { findViewById<TextView>(R.id.imgNo) }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
//        applyAttributes(attrs)
    }

    init {
        orientation = VERTICAL
        inflateContent()
    }

    private fun inflateContent() = LayoutInflater.from(context).inflate(R.layout.image_slider, this, true)

    fun setImgUrls(urls: List<String>) {
        adapter.setImgUrls(urls)
        updatePageNumber()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        imagePager.adapter = adapter
        imagePager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) = updatePageNumber()
        })
    }

    private fun updatePageNumber() {
        countText.text = context.getString(R.string.counterTemplate, imagePager.currentItem + 1, adapter.count)
    }
}

class ImageAdapter : PagerAdapter() {

    private var imgUrls = emptyList<String>()

    fun setImgUrls(urls: List<String>) {
        imgUrls = urls
        notifyDataSetChanged()
    }

    override fun destroyItem(container: ViewGroup, position: Int, item: Any) = container.removeView(item as View)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val currUrl = imgUrls[position]
        val imgView = LayoutInflater.from(container.context).inflate(R.layout.image_slide, container, false) as ImageView
        Picasso.get()
                .load(currUrl)
                .resize(container.width, container.height)
                .centerCrop()
                .into(imgView)
        container.addView(imgView)
        return imgView
    }

    override fun isViewFromObject(view: View, item: Any) = view == item

    override fun getCount() = imgUrls.size
}