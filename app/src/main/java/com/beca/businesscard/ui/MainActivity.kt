package com.beca.businesscard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.beca.businesscard.App
import com.beca.businesscard.databinding.ActivityMainBinding
import com.beca.businesscard.util.Image

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    //injetando o repositorio dentro do viewmodel. Assim respondendo a view
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvCards.adapter = adapter

        getAllBusinessCard()
        insertListener()
    }

    private fun insertListener() {
        binding.fab.setOnClickListener{
            val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = {card ->
            Image.share(this@MainActivity, card)
        }
    }
    //Buscar e ficar notificando as alterações pra recuperar as informações dos cartoes de visitas
    private fun getAllBusinessCard(){
        mainViewModel.getAll().observe(this) { businesscards ->
            //Passar a lista para submeter ao adapter
            adapter.submitList(businesscards)
        }
    }
}