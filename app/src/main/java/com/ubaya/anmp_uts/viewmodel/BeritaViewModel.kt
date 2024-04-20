package com.ubaya.anmp_uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.anmp_uts.model.Berita

class BeritaViewModel(application: Application): AndroidViewModel(application) {
    val beritasLD = MutableLiveData<ArrayList<Berita>>()
//    val beritaLoadErrorLD = MutableLiveData<Boolean>()
//    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun refresh() {

        Log.d("CEKMASUK","masukvolley")
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/berita/beritas.json"
//        val url = "http://127.0.0.1/anmp_uts/read-cerita.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Berita>>() {}.type
                val result = Gson().fromJson<List<Berita>>(it,sType)
                beritasLD.value = result as ArrayList<Berita>?
                Log.d("showvolley",result.toString())
            },
            {
                Log.d("showvolley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}