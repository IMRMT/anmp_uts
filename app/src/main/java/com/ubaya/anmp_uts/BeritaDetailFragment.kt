package com.ubaya.anmp_uts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.ubaya.anmp_uts.databinding.FragmentBeritaDetailBinding
import com.ubaya.anmp_uts.model.Berita
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

        binding.berita = Berita("","","","","","https://picsum.photos/200/300")
        if(arguments!=null){
//            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
//            viewModel.fetchDetail(BeritaDetailFragmentArgs.fromBundle(requireArguments()).beritaId)
//
//            observeViewModel()
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            val uuid = BeritaDetailFragmentArgs.fromBundle(requireArguments()).beritaId
            viewModel.fetchDetail(uuid.toString())

            binding.nextListener = this
            binding.prevListener = this

            observeViewModel()
        }

    }
    fun observeViewModel() {
//        viewModel.detailLD.observe(viewLifecycleOwner, Observer {
//            if(it == null){
//
//            }else{
//                binding.txtTitle.setText(it.title)
//                binding.txtGenre.setText(it.genre)
//                binding.txtName.setText("@${it.author}")
//                val picasso = Picasso.Builder(binding.root.context)
//                picasso.listener { picasso, uri, exception -> exception.printStackTrace() }
//                picasso.build().load(it.images).into(binding.imageView)
//
//            }
//
//            //Multi Page
//            val berita = it.para
//            val size = berita?.size ?: 0
//            var index = 0
//
//            if(size > 0){
//                binding.txtPara.text = berita?.get(index)
//                binding.btnPrev.isEnabled = false
//
//                binding.btnNext.setOnClickListener {
//                    index++
//                    binding.txtPara.text = berita?.get(index)
//                    binding.btnPrev.isEnabled = true
//                    if(index == size - 1) {
//                        binding.btnNext.isEnabled = false
//                    }
//                }
//
//                binding.btnPrev.setOnClickListener {
//                    index--
//                    binding.txtPara.text = berita?.get(index)
//                    binding.btnNext.isEnabled = true
//                    if(index == 0){
//                        binding.btnPrev.isEnabled = false
//                    }
//                }
//            }
// `       })
        viewModel.detailLD.observe(viewLifecycleOwner,{
            binding.berita = it//detail berita bawaan dari variable
        })
        viewModel.paraLD.observe(viewLifecycleOwner,{
            binding.para = it//paragraf berita bawaan dari variable
        })
    }

    override fun onNextClick(v: View) {
//        if (v.tag.toString().toInt() > 0){
//            binding.para!!.uuid = v.tag.toString().toInt() + 1
//        }
        binding.para?.let {
            it.uuid += 1
            viewModel.fetchDetail(it.uuid.toString())
        }
    }

    override fun onPrevClick(v: View) {
        if (v.tag.toString().toInt() > 0){
            binding.para!!.uuid = v.tag.toString().toInt() - 1
        }
    }
}