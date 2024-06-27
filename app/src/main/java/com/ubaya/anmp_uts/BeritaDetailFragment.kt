package com.ubaya.anmp_uts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.ubaya.anmp_uts.databinding.FragmentBeritaDetailBinding
import com.ubaya.anmp_uts.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class BeritaDetailFragment : Fragment(), NextClickListener, PrevClickListener {
    private lateinit var binding: FragmentBeritaDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBeritaDetailBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null){
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetchDetail(BeritaDetailFragmentArgs.fromBundle(requireArguments()).beritaId)

            observeViewModel()
        }
    }
    fun observeViewModel() {
        viewModel.detailLD.observe(viewLifecycleOwner, Observer {
            if(it == null){

            }else{
                binding.txtTitle.setText(it.title)
                binding.txtGenre.setText(it.genre)
                binding.txtName.setText("@${it.author}")
                val picasso = Picasso.Builder(binding.root.context)
                picasso.listener { picasso, uri, exception -> exception.printStackTrace() }
                picasso.build().load(it.images).into(binding.imageView)

            }

            //Multi Page
            val berita = it.para
            val size = berita?.size ?: 0
            var index = 0

            if(size > 0){
                binding.txtPara.text = berita?.get(index)
                binding.btnPrev.isEnabled = false

                binding.btnNext.setOnClickListener {
                    index++
                    binding.txtPara.text = berita?.get(index)
                    binding.btnPrev.isEnabled = true
                    if(index == size - 1) {
                        binding.btnNext.isEnabled = false
                    }
                }

                binding.btnPrev.setOnClickListener {
                    index--
                    binding.txtPara.text = berita?.get(index)
                    binding.btnNext.isEnabled = true
                    if(index == 0){
                        binding.btnPrev.isEnabled = false
                    }
                }
            }
        })
    }

    override fun onNextClick(v: View) {
        val para = v.tag.toString().toInt()
        holder.binding.para = beritaList[position]
        var index = 0
        if(size > 0){
            binding.txtPara.text = para[]
            binding.btnPrev.isEnabled = false

            index++
            binding.txtPara.text = para?.get(index)
            binding.btnPrev.isEnabled = true
            if(index == size - 1) {
                binding.btnNext.isEnabled = false
            }
        }
        //masih nunggu database paragraf
    }

    override fun onPrevClick(v: View) {
        TODO("Not yet implemented")
    }
}