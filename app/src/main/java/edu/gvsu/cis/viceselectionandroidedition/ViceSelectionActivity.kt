package edu.gvsu.cis.viceselectionandroidedition

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ViceSelectionActivity : AppCompatActivity() {

    private var selection : String? = "Steak"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_vice_selection)
        setSupportActionBar(findViewById(R.id.toolbar))

        if (intent.hasExtra("vice")) {
            selection = intent.getStringExtra("vice")
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val intent = Intent()
            intent.putExtra("vice", selection)
            setResult(RESULT_OK, intent)
            finish()
        }

        val spinner = findViewById<Spinner>(R.id.choices)
        val adapter = ArrayAdapter.createFromResource(
            this, R.array.vices,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
        )
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val pos = adapter.getPosition(selection)
        spinner.setSelection(pos)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View?, i: Int, l: Long) {
                selection = adapterView.getItemAtPosition(i) as String
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

    }

}