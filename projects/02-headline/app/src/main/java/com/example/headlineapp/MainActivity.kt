package com.example.headlineapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val titles = arrayOf(
        "长大嫁人以后，有人教你做饭，也要有人陪你吃饭",
        "睡前两个小细节，如果能坚持做，身体会慢慢感谢你",
        "实拍身体代谢慢了的你，往往都有这几个生活习惯",
        "风湿病最喜欢大米饭、腰果和土豆，多留意日常饮食",
        "久坐、久盯一处，比眼睛疲劳更可怕，眼睛也需要放松",
        "生活方式一变，身体感受最明显，规律作息真的很重要"
    )

    private val sources = arrayOf("魅力人生", "夜读书房", "健康时报", "养生百科", "生活观察", "每日精选")
    private val comments = arrayOf("9804评论", "198评论", "786评论", "304评论", "119评论", "87评论")
    private val times = arrayOf("1小时前", "2小时前", "3小时前", "4小时前", "1天前", "2天前")

    private val singleImages = intArrayOf(
        R.drawable.table,
        R.drawable.wireclothes,
        R.drawable.apple
    )

    private val tripleImages = intArrayOf(
        R.drawable.scarf,
        R.drawable.kiwifruit,
        R.drawable.cake,
        R.drawable.table,
        R.drawable.apple,
        R.drawable.wireclothes
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rv_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        recyclerView.adapter = NewsAdapter(buildNewsList())
    }

    private fun buildNewsList(): List<NewsBean> {
        val newsList = mutableListOf<NewsBean>()
        for (index in titles.indices) {
            val images: List<Int>
            val type: Int
            val isTop: Boolean
            when (index) {
                0 -> {
                    type = NewsAdapter.TYPE_SINGLE
                    images = emptyList()
                    isTop = true
                }

                1 -> {
                    type = NewsAdapter.TYPE_SINGLE
                    images = listOf(singleImages[0])
                    isTop = false
                }

                2 -> {
                    type = NewsAdapter.TYPE_MULTI
                    images = listOf(tripleImages[0], tripleImages[1], tripleImages[2])
                    isTop = false
                }

                3 -> {
                    type = NewsAdapter.TYPE_SINGLE
                    images = listOf(singleImages[1])
                    isTop = false
                }

                4 -> {
                    type = NewsAdapter.TYPE_MULTI
                    images = listOf(tripleImages[3], tripleImages[4], tripleImages[5])
                    isTop = false
                }

                else -> {
                    type = NewsAdapter.TYPE_SINGLE
                    images = listOf(singleImages[2])
                    isTop = false
                }
            }

            newsList.add(
                NewsBean(
                    id = index + 1,
                    title = titles[index],
                    source = sources[index],
                    comment = comments[index],
                    time = times[index],
                    type = type,
                    images = images,
                    isTop = isTop
                )
            )
        }
        return newsList
    }
}
