package br.edu.isfp.dmo5.projetofinaldmos5.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.edu.isfp.dmo5.projetofinaldmos5.R
import br.edu.isfp.dmo5.projetofinaldmos5.databinding.ActivityMainBinding
import br.edu.isfp.dmo5.projetofinaldmos5.ui.register.SingupActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setUpListeners()
    }

    private fun setUpListeners(){
        binding.textSingup.setOnClickListener{
            singupIntent()
        }
    }

    private fun singupIntent(){
        val mIntent = Intent(this, SingupActivity::class.java)
        startActivity(mIntent)
    }

}