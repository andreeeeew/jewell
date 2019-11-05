package com.example.jewell

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jewell.adapter.SimpleRecyclerViewAdapter


private lateinit var recyclerView: RecyclerView
private lateinit var recyclerViewAdapter: RecyclerView.Adapter<*>
private lateinit var recylerViewLayoutManager: RecyclerView.LayoutManager
private lateinit var subjects: Array<String>

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, BottomBarActivity::class.java)
        setContentView(R.layout.activity_main)
        startActivity(intent)

        var subjects = arrayOf(
            "Nougat",
            "Marshmallow",
            "Lollipop",
            "KitKat",
            "Jelly Bean",
            "Ice Cream Sandwich",
            "Gingerbread",
            "Cupcake",
            "dunut",
            "eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb"
        )

        recyclerView = findViewById(R.id.recyclerview) as RecyclerView

        recylerViewLayoutManager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(recylerViewLayoutManager)

        recyclerViewAdapter = SimpleRecyclerViewAdapter(this@MainActivity, subjects)
        recyclerView.setAdapter(recyclerViewAdapter)


//        val recyclerView = findViewById(R.id.recycler_view) as RecyclerView
//
//// Horizontal
//        OverScrollDecoratorHelper.setUpOverScroll(
//            recyclerView,
//            OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL
//        )
//// Vertical
//        OverScrollDecoratorHelper.setUpOverScroll(
//            recyclerView,
//            OverScrollDecoratorHelper.ORIENTATION_VERTICAL
//        )
    }
}
