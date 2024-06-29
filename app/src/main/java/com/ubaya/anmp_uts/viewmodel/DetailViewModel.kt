package com.ubaya.anmp_uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ubaya.anmp_uts.model.Berita
import com.ubaya.anmp_uts.model.Paragraf
import com.ubaya.anmp_uts.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class DetailViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    private var job = Job()
    val detailLD = MutableLiveData<Berita>()
    val paraLD = MutableLiveData<List<Paragraf>>()

    fun fetchDetail(uuid:Int){
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/anmp_uts/berita-detail.php?id=${id}"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url, {
//                detailLD.value = Gson().fromJson(it, Berita::class.java)
//                Log.d("showVolley", it)
//            },
//            {
//                Log.d("ShowVolley", it.toString())
//            }
//        )
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
        launch {
            val db = buildDb(getApplication())
            detailLD.postValue(db.hobbyAppDao().selectBeritaTodo(uuid))
        }
    }

    fun getpara(beritaId: Int){
        launch {
            val db = buildDb(getApplication())
            paraLD.postValue(db.hobbyAppDao().selectParagrafTodo(beritaId))
        }
    }

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}