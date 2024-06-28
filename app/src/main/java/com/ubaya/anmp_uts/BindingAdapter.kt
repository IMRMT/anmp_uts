package com.ubaya.anmp_uts

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.ubaya.anmp_uts.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

@BindingAdapter("android:imageUrl")
fun loadPhoto(imageView: ImageView, url:String){
    val picasso = Picasso.Builder(imageView.context)
    picasso.listener{picasso, url, exception -> exception.printStackTrace()}
    picasso.build().load(url).into(imageView)
    imageView.visibility = View.VISIBLE
//        object:Callback{
//        override fun onSuccess(){
//            var v = imageView.parent as View
//            var pb = v.findViewById<ProgressBar>(R.id.progressImage)
//            pb.visibility = View.INVISIBLE
//            imageView.visibility = View.VISIBLE
//        }
//        override fun onError(e: Exception){
//            Log.e("picasso",e?.message.toString())
//        }
//    })
}