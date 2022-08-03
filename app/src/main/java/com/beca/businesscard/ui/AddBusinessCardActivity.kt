package com.beca.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.beca.businesscard.App
import com.beca.businesscard.R
import com.beca.businesscard.data.BusinessCard
import com.beca.businesscard.databinding.ActivityAddBusinessCardBinding

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListener()
    }

    private fun insertListener(){
        binding.btnClose.setOnClickListener{
            finish()
        }
        binding.btnConfirm.setOnClickListener{
            //recebendo os valores que usuarios digita na tela
            val businessCard = BusinessCard(
                nome = binding.tilNome.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                fundoPersonalizado = binding.tilCor.editText?.text.toString(),
            )
            //chama o metodo insert e recebe um businesscard
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_sucess,Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}