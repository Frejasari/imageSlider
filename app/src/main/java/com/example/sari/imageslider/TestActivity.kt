package com.example.sari.imageslider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {
    //    private val randomUrls = List(10) { _ -> "https://source.unsplash.com/random/1920x1080" }
    private val randomUrls = listOf(
            "https://media.licdn.com/dms/image/C4E03AQG6baic8l5IDA/profile-displayphoto-shrink_200_200/0?e=1539216000&v=beta&t=1n4IA3AoZriVEGbUW-SCQ5j4qrS374gfxt2RZu102s4",
            "http://www.nord-com.net/absolut.genial/ffz-bhv/2006/Freja.JPG",
            "http://www.gegenwindundglitzerkram.de/wp-content/uploads/2016/03/bBurdaEasyMantel09.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        imgSlider.setImgUrls(randomUrls)
    }
}
