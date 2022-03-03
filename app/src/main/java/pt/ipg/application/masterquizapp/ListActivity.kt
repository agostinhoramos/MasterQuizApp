package pt.ipg.application.masterquizapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

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

        SocketHandler.setSocket()
        val mSocket = SocketHandler.getSocket()
        mSocket.connect()

        // Call current list
        mSocket.emit("initList")

        // Waiting event..
        mSocket.on("list"){ args ->
            if (args[0] != null){
                val strObj = args[0] as String
                runOnUiThread{

                    var jsonArray = JSONArray(strObj)
                    for(i in 0 until jsonArray.length()){

                        // Covert json object
                        var name = jsonArray.getJSONObject(i).getString("name")
                        var score = jsonArray.getJSONObject(i).getString("score")

                        resultList.add(
                            Result("$name", "Final score: $score%", R.drawable.ukraine_flag)
                        )
                    }

                    adapter = RecyclerAdapter(this, resultList)
                    recyclerView.adapter = adapter
                }
            }
        }
    }
}