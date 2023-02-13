package fr.epsi.marketapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.widget.TextView

class StudentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        setHeaderTitle(getString(R.string.headerStudentTitle))
        showBackButton()

        val epsiUrlTextView = findViewById<TextView>(R.id.epsiUrl)
        epsiUrlTextView.setOnClickListener{
            val url = Uri.parse("http://www.epsi.fr/")
            val intent = Intent(Intent.ACTION_VIEW, url)
            startActivity(intent)
        }
    }
}