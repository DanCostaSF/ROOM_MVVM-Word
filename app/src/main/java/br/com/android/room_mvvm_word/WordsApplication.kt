package br.com.android.room_mvvm_word

import android.app.Application
import br.com.android.room_mvvm_word.data.WordRoomDatabase
import br.com.android.room_mvvm_word.data.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao())}
}