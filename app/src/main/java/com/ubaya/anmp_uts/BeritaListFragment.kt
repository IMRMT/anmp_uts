package com.ubaya.anmp_uts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.anmp_uts.databinding.FragmentBeritaListBinding
import com.ubaya.anmp_uts.viewmodel.BeritaViewModel

class BeritaListFragment : Fragment() {
    private lateinit var binding: FragmentBeritaListBinding
    private lateinit var viewModel: BeritaViewModel
    private val fighterListAdapter  = BeritaListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBeritaListBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(BeritaViewModel::class.java)
        viewModel.refresh()

        binding.recViewBerita.layoutManager = LinearLayoutManager(context)
        binding.recViewBerita.adapter = fighterListAdapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.beritasLD.observe(viewLifecycleOwner, Observer {
            fighterListAdapter.updateBeritaList(it)
        })
        viewModel.beritaLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.txtError?.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recViewBerita.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recViewBerita.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })
    }
}