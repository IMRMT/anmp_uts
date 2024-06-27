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

class BeritaListAdapter(val beritaList:ArrayList<Berita>): RecyclerView.Adapter<BeritaListAdapter.BeritaViewHolder>(), BeritaClickListener {

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

    fun updateBeritaList(newStudentList: List<Berita>) {
        beritaList.clear()
        beritaList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
//        holder.binding.txtTitle.text = beritaList[position].title
//        holder.binding.txtName.text = beritaList[position].author
//        holder.binding.txtDesc.text = beritaList[position].descript
//        holder.binding.txtGenre.text = beritaList[position].genre
//        holder.binding.txtId.text = beritaList[position].uuid.toString()
//
//        holder.binding.btnDetail.setOnClickListener {
//            val id = holder.binding.txtId.text.toString()
//            val beritaId = Integer.parseInt(id)
//            val action = BeritaListFragmentDirections.actionBeritaDetail(beritaId)
//            Navigation.findNavController(it).navigate(action)
//        }

        holder.binding.berita = beritaList[position]
        //databinding bisa dipake kalo ui rame/banyakk
        holder.binding.readListener = this//ini karena yang ditekan adalah dirinya sendiri

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(beritaList[position].images)
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

    override fun onBeritaClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action  = BeritaListFragmentDirections.actionBeritaDetail(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}