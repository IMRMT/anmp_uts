package com.ubaya.anmp_uts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.anmp_uts.databinding.FragmentBeritaDetailBinding
import com.ubaya.anmp_uts.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class BeritaDetailFragment : Fragment() {
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

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.refresh()


        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.beritaLD.observe(viewLifecycleOwner, Observer {
            if(arguments != null) {
                val id = BeritaDetailFragmentArgs.fromBundle(requireArguments()).beritaId
                binding.txtId.setText(id)
            }
            binding.txtName.setText(it.author)
            binding.txtTitle.setText(it.title)
            binding.txtPara.setText(it.para)
            binding.txtGenre.setText(it.genre)
        })
    }
}