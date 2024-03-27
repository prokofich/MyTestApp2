package com.ddd.mytestapp2.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.ddd.mytestapp2.R
import com.ddd.mytestapp2.databinding.FragmentDetailBinding
import com.ddd.mytestapp2.model.constant.MAIN
import com.ddd.mytestapp2.model.modelFood.Food

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding?.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val food = requireArguments().getParcelable<Food>("key1")

        binding?.idDetailIv?.load(food?.image)
        binding?.idDetailTvName?.text = food?.title
        binding?.idDetailTvDesc?.text = "id = ${food?.id}"

        binding?.idDetailButtonBack?.setOnClickListener {
            MAIN?.navController?.navigate(R.id.action_detailFragment_to_menuFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}