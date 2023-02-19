package fr.epsi.marketapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.epsi.marketapp.R
import fr.epsi.marketapp.model.Product
import com.squareup.picasso.Picasso
import fr.epsi.marketapp.ProductDetailsActivity

class ProductAdapter (val products: ArrayList<Product>): RecyclerView.Adapter<ProductAdapter.ViewHolder> () {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageView = view.findViewById<ImageView>(R.id.layout_product_ImageView)
        val productNameTextView = view.findViewById<TextView>(R.id.layout_product_name)
        val productDescriptionTextView = view.findViewById<TextView>(R.id.layout_product_description)
        val container = view.findViewById<LinearLayout>(R.id.layout_product_linearLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products.get(position)

        holder.productNameTextView.text = product.name
        val shortDescriprion = product.description.split('\n')
        holder.productDescriptionTextView.text = shortDescriprion.first().plus(shortDescriprion.get(1))
        Picasso.get().load(product.picture_url).into(holder.imageView)

        holder.container.setOnClickListener {
            val newIntent = Intent(holder.imageView.context, ProductDetailsActivity::class.java)
            newIntent.putExtra("title", product.name)
            newIntent.putExtra("picture_url", product.picture_url)
            newIntent.putExtra("description", product.description)

            holder.itemView.context.startActivity(newIntent)
        }
    }
}