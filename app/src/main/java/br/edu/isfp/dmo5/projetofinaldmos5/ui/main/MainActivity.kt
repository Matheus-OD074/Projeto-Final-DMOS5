package br.edu.isfp.dmo5.projetofinaldmos5.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.edu.isfp.dmo5.projetofinaldmos5.R
import br.edu.isfp.dmo5.projetofinaldmos5.data.model.User
import br.edu.isfp.dmo5.projetofinaldmos5.databinding.ActivityMainBinding
import br.edu.isfp.dmo5.projetofinaldmos5.ui.register.SingupActivity
import br.edu.isfp.dmo5.projetofinaldmos5.ui.users.UsersActivity
import br.edu.isfp.dmo5.projetofinaldmos5.util.Constant
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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
        binding.btnLogin.setOnClickListener {
            loginUser()
            //teste()
        }
    }

    private fun singupIntent(){
        val mIntent = Intent(this, SingupActivity::class.java)
        startActivity(mIntent)
    }
    private fun teste(){

        val colection = Firebase.firestore.collection("chat")
            .document("teste")
            .get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val document: DocumentSnapshot = it.result
                    if(document.exists()){
                        Log.v("TESTE1", "USER: ${document.data}")
                    }else{

                    }
                }
            }

    }

    private fun  loginUser(){
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        //if(viewModel.login(email, password)){
            val mIntent = Intent(this, UsersActivity::class.java)
            mIntent.putExtra(Constant.KEY_LOGGEDUSER, email)
            startActivity(mIntent)
        //}
    }


}