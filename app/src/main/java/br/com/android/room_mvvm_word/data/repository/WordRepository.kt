package br.com.android.room_mvvm_word.data.repository

import androidx.annotation.WorkerThread
import br.com.android.room_mvvm_word.data.daos.WordDao
import br.com.android.room_mvvm_word.data.models.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    @Suppress
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

}