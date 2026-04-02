package com.example.malllist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val goodsList = listOf(
        Goods(
            "针织毛衣",
            "加厚保暖针织毛衣，面料柔软亲肤，适合秋冬日常通勤穿搭。",
            R.drawable.wireclothes
        ),
        Goods(
            "户外圆桌",
            "复古风格小圆桌，阳台与庭院都适用，适合下午茶和休闲会客。",
            R.drawable.table
        ),
        Goods(
            "轻盈围巾",
            "渐变色系长款围巾，垂坠感自然，春秋季可作穿搭点缀。",
            R.drawable.scarf
        ),
        Goods(
            "奇异果礼盒",
            "新鲜奇异果酸甜多汁，富含维生素，适合作为家庭水果补给。",
            R.drawable.kiwifruit
        ),
        Goods(
            "奶油蛋糕",
            "奶油裱花蛋糕造型精致，适合生日聚会、纪念日庆祝等场景。",
            R.drawable.cake
        ),
        Goods(
            "红苹果",
            "果形饱满、口感清甜爽脆，适合作为日常健康零食和早餐搭配。",
            R.drawable.apple
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GoodsAdapter(goodsList)
    }
}
