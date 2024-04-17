package com.ubaya.anmp_uts

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.anmp_uts.databinding.BeritaListItemBinding
import com.ubaya.anmp_uts.model.Berita
import java.lang.Exception

class BeritaListAdapter(val beritaList:ArrayList<Berita>): RecyclerView.Adapter<BeritaListAdapter.BeritaViewHolder>() {

    class BeritaViewHolder(var binding: BeritaListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val binding = BeritaListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return BeritaViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return beritaList.size
    }

    fun updateBeritaList(newStudentList: ArrayList<Berita>) {
        beritaList.clear()
        beritaList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        holder.binding.txtTitle.text = beritaList[position].title
        holder.binding.txtName.text = beritaList[position].author
        holder.binding.txtPara.text = beritaList[position].para
        holder.binding.txtGenre.text = beritaList[position].genre

        holder.binding.btnDetail.setOnClickListener {
            val id = holder.binding.txtId.text.toString()
            val beritaId = Integer.parseInt(id)
            val action = BeritaListFragmentDirections.actionBeritaDetail(beritaId)
            Navigation.findNavController(it).navigate(action)
        }

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(beritaList[position].url)
            .into(holder.binding.imageView, object: Callback {
                override fun onSuccess() {
                    holder.binding.progressImage.visibility = View.INVISIBLE
                    holder.binding.imageView.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    Log.d("Picasso_error", e.toString())
                }
            })
    }
}