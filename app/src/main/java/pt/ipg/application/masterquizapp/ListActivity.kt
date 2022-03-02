package pt.ipg.application.masterquizapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        supportActionBar!!.title = "Top result"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val resultList = ArrayList<Result>()
        resultList.add(
            Result("Pedro Lucas", "Final score: 55 %", R.drawable.ukraine_flag)
        )
        resultList.add(
            Result("Carlos Santos", "Final score: 45 %", R.drawable.ukraine_flag)
        )

        adapter = RecyclerAdapter(this, resultList)
        recyclerView.adapter = adapter
    }
}