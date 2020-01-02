package com.example.uselessapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var usefulViewModel: com.example.uselessapp.UsefulViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.roomdata.R.layout.activity_main)


        //Initialize a RecycleView adapter
        val recyclerView = findViewById<RecyclerView>(com.example.roomdata.R.id.recyclerview)
        val adapter = com.example.uselessapp.UsefulAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Initialize the ViewModel
        usefulViewModel= ViewModelProvider(this).get(com.example.uselessapp.UsefulViewModel::class.java)
        usefulViewModel.allUseful.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setUsefulRecords(it) }
        })


        fab.setOnClickListener{
            val intent = Intent(this, com.example.uselessapp.AddActivity::class.java)
            startActivityForResult(intent,com.example.uselessapp.MainActivity.Companion.REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == com.example.uselessapp.MainActivity.Companion.REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                //TODO : Save a useful record to the database
                data?.let {
                    val useful = com.example.uselessapp.Useful(
                        0,
                        it.getStringExtra(com.example.uselessapp.AddActivity.Companion.EXTRA_NAME),
                        it.getStringExtra(com.example.uselessapp.AddActivity.Companion.EXTRA_AGE).toInt()
                    )
                    usefulViewModel.insertUseful(useful)
                    Toast.makeText(this, "Record saved", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
    companion object{
        const val REQUEST_CODE=1
    }
}
