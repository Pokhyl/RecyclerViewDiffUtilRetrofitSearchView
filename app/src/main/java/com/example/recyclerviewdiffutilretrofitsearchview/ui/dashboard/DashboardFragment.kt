package com.example.recyclerviewdiffutilretrofitsearchview.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdiffutilretrofitsearchview.MainRepository
import com.example.recyclerviewdiffutilretrofitsearchview.Movie
import com.example.recyclerviewdiffutilretrofitsearchview.RetrofitService
import com.example.recyclerviewdiffutilretrofitsearchview.RvAdapter
import com.example.recyclerviewdiffutilretrofitsearchview.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    lateinit var viewModel: DashboardViewModel

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val retrofitService = RetrofitService.getInstans()
        val mainRepository = MainRepository(retrofitService)


        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val adapter = RvAdapter()
        binding.reciclerViewMovie.adapter = adapter
        binding.reciclerViewMovie.layoutManager = LinearLayoutManager(context?.applicationContext)
        viewModel.getAllMovie()
        viewModel.movieList.observe(viewLifecycleOwner){
        adapter.setMovies(it)
            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    adapter?.getFilter()?.filter(p0)
                    return true
                }

            })



        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}