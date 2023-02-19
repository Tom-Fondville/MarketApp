package fr.epsi.marketapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProductDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        val title: String? = intent.extras!!.getString("title")
        setHeaderTitle(title.toString())
        showBackButton()


        val imageView = findViewById<ImageView>(R.id.product_details_ImageView)
        val picture_url = intent.extras!!.getString("picture_url")
        Picasso.get()
            .load(picture_url)
            .into(imageView)

        val descriptionTextView = findViewById<TextView>(R.id.product_details_description_textView)
        val description = intent.extras!!.getString("description")
        descriptionTextView.text = description
//        newIntent.putExtra("title", product.name)
//        newIntent.putExtra("picture_url", product.picture_url)
//        newIntent.putExtra("description", product.description)
    }
}