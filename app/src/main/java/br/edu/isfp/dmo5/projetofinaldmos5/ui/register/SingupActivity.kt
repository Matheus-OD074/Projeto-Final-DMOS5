package br.edu.isfp.dmo5.projetofinaldmos5.ui.register

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.edu.isfp.dmo5.projetofinaldmos5.R
import br.edu.isfp.dmo5.projetofinaldmos5.databinding.ActivitySingupBinding

class SingupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySingupBinding
    lateinit var viewModel: SingupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(SingupViewModel::class.java)

        setUpListeners()
        setUpOberservers()
    }

    private fun setUpListeners(){
        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun setUpOberservers(){
        viewModel.inserted.observe(this, Observer {
            val str = if(it){
                "User saved sucessful"
            }else{
                "Error on saving User."
            }
            Toast.makeText(this,str, Toast.LENGTH_SHORT).show()
        })

        viewModel.allRinserted.observe(this, Observer {
            val str = if(it){
                "User already Resgistered!"
            }else{
                "Error on saving User."
            }
            Toast.makeText(this,str, Toast.LENGTH_SHORT).show()
        })
    }

    private fun registerUser(){

        val strEmail = binding.editTextEmail.text.toString()
        val strSenha = binding.editTextPassword.text.toString()
        val strName = binding.editTextName.text.toString()

        viewModel.insert(strEmail, strSenha, strName)

        finish()

    }

}