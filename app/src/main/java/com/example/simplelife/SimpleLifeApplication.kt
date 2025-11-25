package com.example.simplelife

import android.app.Application
import com.example.simplelife.data.AppDatabase
import com.example.simplelife.data.SimpleLifeRepository

class SimpleLifeApplication : Application() {
    // Create the database and repository only when the app starts
    // 'by lazy' means we don't create it until we need it
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { SimpleLifeRepository(database) }
}