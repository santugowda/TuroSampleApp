package com.example.turosampleapp

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.turosampleapp.viewmodel.YelpViewModel
import kotlinx.android.synthetic.main.fragment_user_search.*


class FoodTypeLocationSharedViewModel : ViewModel() {
    val foodTypeEntered = MutableLiveData<Map<String, String>>()

    fun foodTypeToQuery(foodTypeLocation: Map<String, String>) {
        foodTypeEntered.value = foodTypeLocation
    }
}
class UserSearchFragment : Fragment() {

    private lateinit var yelpViewModel: YelpViewModel
    private lateinit var foodTypeLocationSharedViewModel: FoodTypeLocationSharedViewModel
    val foodTypeLocation : MutableMap<String, String> = HashMap()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFoodNPlaceRequested()
        yelpViewModel = ViewModelProvider(this).get(YelpViewModel::class.java)
        foodTypeLocationSharedViewModel =
            ViewModelProvider(requireActivity()).get(FoodTypeLocationSharedViewModel::class.java)
        view.findViewById<Button>(R.id.searchButton).setOnClickListener {
            foodTypeLocationSharedViewModel.foodTypeToQuery(foodTypeLocation)
            findNavController().navigate(R.id.action_UserSearchFragment_to_RestaurantListFragment)
        }
    }

    private fun getFoodNPlaceRequested() {
        food_type.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                foodTypeLocation["term"] = s.toString()
            }
        })

        food_location.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                foodTypeLocation["location"] = s.toString()
            }
        })
        food_location.onFocusChangeListener = onFocusChangeListener
        food_type.onFocusChangeListener = onFocusChangeListener
    }

    private var onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
        if (!hasFocus) {
            hideKeyboard(v)
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager: InputMethodManager = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}