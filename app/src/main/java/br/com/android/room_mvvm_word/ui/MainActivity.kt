 package br.com.android.room_mvvm_word.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.android.room_mvvm_word.WordsApplication
import br.com.android.room_mvvm_word.data.models.Word
import br.com.android.room_mvvm_word.databinding.ActivityMainBinding
import br.com.android.room_mvvm_word.ui.adapters.WordListAdapter
import br.com.android.room_mvvm_word.ui.viewmodels.WordViewModel
import br.com.android.room_mvvm_word.ui.viewmodels.WordViewModelFactory

 class MainActivity : AppCompatActivity() {

     private lateinit var binding : ActivityMainBinding

     private var _wordViewModel : WordViewModel? = null
     private val wordViewModel get() = _wordViewModel!!

     private lateinit var adapter : WordListAdapter

     private var resultLauncher = registerForActivityResult(ActivityResultContracts
         .StartActivityForResult()) { result ->
         if (result.resultCode == Activity.RESULT_OK) {
             result.data?.let {
                 wordViewModel.insert(Word(it.getStringExtra("REPLY").toString()))
             }

         }
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _wordViewModel =
            ViewModelProvider(
            this,
            WordViewModelFactory((application as WordsApplication)
                .repository))[WordViewModel::class.java]
        adapter = WordListAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)


    }

     override fun onStart() {
         super.onStart()
         wordViewModel.allWords.observe(this, Observer { words ->
             words?.let { adapter.submitList(words) }
         })

         binding.fab.setOnClickListener {
             resultLauncher.launch(Intent(this, NewWordActivity::class.java))
         }

     }
}