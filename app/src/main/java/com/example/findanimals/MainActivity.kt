package com.example.findanimals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findanimals.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    var name : String = ""
    var animalDetailList = ArrayList<Animal>()
    lateinit var adapter : AnimalAdapter
    private val baseUrl : String = "https://api.api-ninjas.com/"
    private val apiKey : String = <your_api_key>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.recyclerView.layoutManager =LinearLayoutManager(this@MainActivity)

        mainBinding.buttonSearch.setOnClickListener {

            if(mainBinding.editTextName.text.toString().isNotEmpty()) {
                name = mainBinding.editTextName.text.toString()
                mainBinding.progressBar.isVisible = true
                showAnimalDetails()
            }else{
                Toast.makeText(applicationContext, "Enter the topic", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun showAnimalDetails() {

        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI : RetrofitAPI =retrofit.create(RetrofitAPI::class.java)
        val call :Call<List<Animal>> =retrofitAPI.getAnimalDetails(apiKey, name.trim())

        call.enqueue(object: Callback<List<Animal>>{
            override fun onResponse(call: Call<List<Animal>>, response: Response<List<Animal>>) {
                if (response.isSuccessful){
                    if (response.body()!!.isNotEmpty()) {
                        mainBinding.recyclerView.isVisible = true
                        mainBinding.progressBar.isVisible = false
                        animalDetailList = response.body() as ArrayList<Animal>
                        adapter = AnimalAdapter(this@MainActivity, animalDetailList)
                        mainBinding.recyclerView.adapter = adapter
                    }
                    else if (response.body()!!.isEmpty()){
                        mainBinding.textViewResult.text = "Not found.\nCheck the name and try again."
                        mainBinding.progressBar.isVisible = false
                    }
                }else{
                    Toast.makeText(applicationContext, response.message(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
                mainBinding.progressBar.isVisible = false
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

    }
}
