package com.example.kakaoimglibrary.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kakaoimglibrary.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewPagerAdapter by lazy { ViewPagerAdapter(this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() = with(binding) {
        viewPager.adapter = viewPagerAdapter
        // TabLayout x ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setText(viewPagerAdapter.getTitle(position))
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
