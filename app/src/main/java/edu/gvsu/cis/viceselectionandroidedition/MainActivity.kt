package edu.gvsu.cis.viceselectionandroidedition

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private var vice = "Steak"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var viceSelectionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val tv = findViewById<TextView>(R.id.message)
                vice = data?.getStringExtra("vice") ?: "Steak"
                tv.text = "Your vice is: " + vice
            }
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, ViceSelectionActivity::class.java)
            intent.putExtra("vice", vice)
            viceSelectionLauncher.launch(intent)
        }
    }

}