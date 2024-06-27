package com.ubaya.anmp_uts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.anmp_uts.model.HobbyAppDatabase
import com.ubaya.anmp_uts.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val allUserLD = MutableLiveData<List<User>>()
    val userLD = MutableLiveData<User>()
    private var job = Job()

    fun fetchAll() {
        launch {
            val db = HobbyAppDatabase.buildDatabase(getApplication())
            allUserLD.postValue(db.hobbyAppDao().selectAllUser())
        }
    }

    fun fetch(id:Int) {
        launch {
            val db = HobbyAppDatabase.buildDatabase(getApplication())
            userLD.postValue(db.hobbyAppDao().selectUser(id))
        }
    }

    fun newUser(user: User) {
        launch {
            val db = HobbyAppDatabase.buildDatabase(getApplication())
            db.hobbyAppDao().newUser(user)
        }
    }

    fun updateUser(user: User) {
        launch {
            val db = HobbyAppDatabase.buildDatabase(getApplication())
            db.hobbyAppDao().updateUser(user)
            userLD.postValue(db.hobbyAppDao().selectUser(user.uuid))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}