package com.example.findanimals

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Animal (
    val name : String,
    val taxonomy : AnimalDetails,
    val locations : List<String>,
    val characteristics : AnimalCharacteristics
) : Serializable{

    class AnimalCharacteristics(
        val color : String,
        @SerializedName("common_name")
        val commonName : String
    ) : Serializable{

    }

    class AnimalDetails(
        val kingdom : String,
        val phylum : String,

        @SerializedName("class")
        val animalClass : String,
        val order : String,
        val family : String,
        val genus : String,

        @SerializedName("scientific_name")
        val scientificName : String
    ) : Serializable{

        override fun toString(): String {
            return "Kingdom : ${kingdom}\nPhylum : ${phylum}\nClass : ${animalClass}\nOrder : ${order}\nFamily : ${family}\nGenus : ${genus}"
        }

    }
}