package fr.epsi.marketapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    fun setHeaderTitle(title: String){
        val titleTextView = findViewById<TextView>(R.id.headerTextView)
        titleTextView.text = title
    }

    fun showBackButton(){
        val backButton = findViewById<ImageView>(R.id.headerBackButtonImageView)
        backButton.visibility = View.VISIBLE
        backButton.setOnClickListener{ finish() }
    }
}