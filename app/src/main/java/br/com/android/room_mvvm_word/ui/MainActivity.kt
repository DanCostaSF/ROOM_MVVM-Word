 package br.com.android.room_mvvm_word.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.android.room_mvvm_word.R
import br.com.android.room_mvvm_word.databinding.ActivityMainBinding
import br.com.android.room_mvvm_word.ui.adapters.WordListAdapter

 class MainActivity : AppCompatActivity() {

     private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = WordListAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)


    }
}