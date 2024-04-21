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

class DetailViewModel(application: Application):AndroidViewModel(application) {
    val detailLD = MutableLiveData<Berita>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetchDetail(id:Int){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp_uts/berita-detail.php?id=${id}"

        val stringRequest = StringRequest(
            Request.Method.GET, url, {
                detailLD.value = Gson().fromJson(it, Berita::class.java)
                Log.d("showVolley", it)
            },
            {
                Log.d("ShowVolley", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}