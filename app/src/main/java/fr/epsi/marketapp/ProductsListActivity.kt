package fr.epsi.marketapp

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epsi.marketapp.adapter.ProductAdapter
import fr.epsi.marketapp.model.Product
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ProductsListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)
        val title: String? = intent.extras!!.getString("title")
        setHeaderTitle(title.toString())
        showBackButton()

        val products = arrayListOf<Product>()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_products)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val productAdapter = ProductAdapter(products)
        recyclerView.adapter = productAdapter

        val httpClient = OkHttpClient().newBuilder().build()
        val url = intent.extras!!.getString("url")
        val request = Request.Builder()
            .get()
            .url(url.toString())
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        httpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {}

            override fun onResponse(call: Call, response: Response) {
                if (response.body == null) return
                val productsResponse = toProductsList(response.body!!.string())
                products.addAll(productsResponse)
                runOnUiThread { productAdapter.notifyDataSetChanged() }
            }

        })
    }

    fun toProductsList(jsonString: String): ArrayList<Product>{
        val productList = arrayListOf<Product>()
        val jsProduct = JSONObject(jsonString)
        val jsArray = jsProduct.getJSONArray("items")

        for (i in 0 until jsArray.length()) {
            val jsCat = jsArray.getJSONObject(i)
            val product = Product(
                jsCat.optString("name", "unknown"),
                jsCat.optString("description", "unknown"),
                jsCat.optString("picture_url", "unknown")
            )
            productList.add(product)
        }
        return productList
    }
}