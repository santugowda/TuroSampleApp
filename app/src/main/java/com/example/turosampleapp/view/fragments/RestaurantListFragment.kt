package com.example.turosampleapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.turosampleapp.FoodTypeLocationSharedViewModel
import com.example.turosampleapp.R
import com.example.turosampleapp.client.NetworkResponse
import com.example.turosampleapp.model.Business
import com.example.turosampleapp.view.adapter.RestaurantAdapter
import com.example.turosampleapp.viewmodel.YelpViewModel
import kotlinx.android.synthetic.main.fragment_restaurant_list.*

class RestaurantListFragment : Fragment(), RestaurantAdapter.OnBusinessEventListener {

    private lateinit var yelpViewModel: YelpViewModel
    private lateinit var foodTypeLocationSharedViewModel: FoodTypeLocationSharedViewModel
    private var restaurantAdapter: RestaurantAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        yelpViewModel = ViewModelProvider(this).get(YelpViewModel::class.java)
        foodTypeLocationSharedViewModel =
            ViewModelProvider(requireActivity()).get(FoodTypeLocationSharedViewModel::class.java)
        val foodTypeLocation = foodTypeLocationSharedViewModel.foodTypeEntered

        restaurantAdapter = RestaurantAdapter(arrayListOf(), this)
        val resultRecyclerView: RecyclerView = view.findViewById(R.id.restaurantRecycler)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        resultRecyclerView.layoutManager = layoutManager
        resultRecyclerView.adapter = restaurantAdapter

        foodTypeLocation.value?.let {
            yelpViewModel.businessSearchResult(it).observe(viewLifecycleOwner, Observer {searchResult ->
                when (searchResult.status) {
                    NetworkResponse.NetworkStatus.SUCCESS -> {
                        listProgress.visibility = View.GONE
                        if(searchResult.data != null){
                            searchResult.data.businesses?.let { business -> restaurantAdapter?.addAll(business) }
                        }
                    }
                    NetworkResponse.NetworkStatus.ERROR -> {

                    }
                    NetworkResponse.NetworkStatus.IN_PROGRESS -> {
                        listProgress.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

    override fun onBusinessSelected(business: Business) {
    }
}