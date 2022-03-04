package pt.ipg.application.masterquizapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import pt.ipg.application.masterquizapp.`object`.Result

class RecyclerAdapter(val context: Context, var resultList: ArrayList<Result>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = resultList[position].title
        holder.itemDesc.text = resultList[position].desc
        holder.itemImage.setImageResource(resultList[position].images)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDesc: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDesc = itemView.findViewById(R.id.item_desc)

            itemView.setOnClickListener{
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked ${resultList[position].title}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}