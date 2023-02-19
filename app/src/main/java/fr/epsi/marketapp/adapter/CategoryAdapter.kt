package fr.epsi.marketapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.epsi.marketapp.ProductsListActivity
import fr.epsi.marketapp.R
import fr.epsi.marketapp.model.Category

class CategoryAdapter (val categories: ArrayList<Category>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val categoryNameTextView = view.findViewById<TextView>(R.id.layout_category_name_textView)
        val container = view.findViewById<LinearLayout>(R.id.layout_category_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories.get(position)
        holder.categoryNameTextView.text = category.title

        holder.container.setOnClickListener {
            val newIntent = Intent(holder.itemView.context, ProductsListActivity::class.java)
            newIntent.putExtra("title", category.title)
            newIntent.putExtra("url", category.products_url)
            holder.itemView.context.startActivity(newIntent)
        }
    }
}