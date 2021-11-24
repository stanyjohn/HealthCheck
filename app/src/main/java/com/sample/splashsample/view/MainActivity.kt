package com.sample.splashsample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.splashsample.adapter.HealthAdapter
import com.sample.splashsample.api.RetrofitService
import com.sample.splashsample.databinding.ActivityMainBinding
import com.sample.splashsample.repository.HealthRepository
import com.sample.splashsample.viewmodal.HealthViewModal
import com.sample.splashsample.viewmodal.HealthViewModalFactory

class MainActivity : AppCompatActivity() {
    lateinit var healthViewModal: HealthViewModal
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = HealthRepository(retrofitService)

        healthViewModal = ViewModelProvider(this, HealthViewModalFactory(mainRepository)).get(
                HealthViewModal::class.java
        )

        val adapter = HealthAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        healthViewModal.healthList.observe(this, {
            println(it.data)
            adapter.setHealthDataList(it.data.health)
        })

        healthViewModal.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        healthViewModal.getHealthAPIDetails()
    }
}