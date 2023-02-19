package fr.epsi.marketapp

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epsi.marketapp.model.Category
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class CategoriesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        setHeaderTitle(getString(R.string.headerProduitsTitle))
        showBackButton()

        val categories = arrayListOf<Category>()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_categories)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val categoryAdapter = CategoryAdapter(categories)
        recyclerView.adapter = categoryAdapter


        val httpClient = OkHttpClient().newBuilder().build()
        val request = Request.Builder()
            .url("https://www.ugarit.online/epsi/categories.json")
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        httpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {}

            override fun onResponse(call: Call, response: Response) {
                if (response.body == null) return
                val categoryResponse = toCategoryList(response.body!!.string())
                categories.addAll(categoryResponse)
                runOnUiThread { categoryAdapter.notifyDataSetChanged() }
            }

        })
    }

    fun toCategoryList(jsonString: String): ArrayList<Category>{
        val categoryList = arrayListOf<Category>()
        val jsCategory = JSONObject(jsonString)
        val jsArray = jsCategory.getJSONArray("items")

        for (i in 0 until jsArray.length()) {
            val jsCat = jsArray.getJSONObject(i)
            val category = Category(
                jsCat.optString("category_id", "unknown"),
                jsCat.optString("title", "unknown"),
                jsCat.optString("products_url", "unknown")
            )
            categoryList.add(category)
        }
        return categoryList
    }
}