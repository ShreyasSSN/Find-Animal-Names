package com.example.findanimals

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findanimals.databinding.AnimalItemBinding

class AnimalAdapter(var context: Context, var animalDetailLists : ArrayList<Animal>)
    : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {
        inner class AnimalViewHolder(val adapterBinding: AnimalItemBinding) :
                RecyclerView.ViewHolder(adapterBinding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val binding = AnimalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return animalDetailLists.size
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.adapterBinding.textViewName.text = animalDetailLists[position].name
        "Scientific Name : ${animalDetailLists[position].taxonomy.scientificName}"
            .also { holder.adapterBinding.textViewScientificName.text = it }
        "Common Name : ${animalDetailLists[position].characteristics.commonName}"
            .also { holder.adapterBinding.textViewCommonName.text = it }

        holder.adapterBinding.linearLayout.setOnClickListener {
            val intent = Intent(context, KnowMoreActivity::class.java)

            intent.putExtra("animalList", animalDetailLists[position])
            context.startActivity(intent)
        }
    }
}
