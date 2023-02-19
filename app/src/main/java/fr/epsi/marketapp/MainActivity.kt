package fr.epsi.marketapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setHeaderTitle(getString(R.string.MainHeaderTitle))

        val infoBtn = findViewById<Button>(R.id.mainInfoBtn)
        infoBtn.setOnClickListener {
            val newIntent = Intent(application, StudentActivity::class.java)
            startActivity(newIntent)
        }

        val productsBtn = findViewById<Button>(R.id.mainProduitsBtn)
        productsBtn.setOnClickListener {
            val newIntent = Intent(application, CategoriesActivity::class.java)
            startActivity(newIntent)
        }
    }
}