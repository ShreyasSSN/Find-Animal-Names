package com.example.findanimals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.findanimals.databinding.ActivityKnowMoreBinding

@Suppress("DEPRECATION")
class KnowMoreActivity : AppCompatActivity() {

    private lateinit var knowMoreBinding : ActivityKnowMoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        knowMoreBinding = ActivityKnowMoreBinding.inflate(layoutInflater)
        val view = knowMoreBinding.root
        setContentView(view)

        val animalDetailList = intent.getSerializableExtra("animalList") as Animal

        knowMoreBinding.textViewKnowName.text = animalDetailList.name
        knowMoreBinding.textViewTaxonomy.text = animalDetailList.taxonomy.toString()
        knowMoreBinding.textViewKnowLocations.text = animalDetailList.locations.toString()
        if (animalDetailList.characteristics.commonName != null){
            "Color : ${animalDetailList.characteristics.color}\nCommon Name : ${animalDetailList.characteristics.commonName}"
                .also { knowMoreBinding.textViewCharacterstics.text = it }
        }else{
            "Color : ${animalDetailList.characteristics.color}\nCommon Name : ${animalDetailList.name}"
                .also { knowMoreBinding.textViewCharacterstics.text = it }
        }

    }

}