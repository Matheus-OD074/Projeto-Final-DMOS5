package br.edu.isfp.dmo5.projetofinaldmos5.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import br.edu.isfp.dmo5.projetofinaldmos5.data.model.User
import br.edu.isfp.dmo5.projetofinaldmos5.data.repository.UserRepository
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

class MainViewModel: ViewModel() {

    private val repository = UserRepository()

    fun login(email: String, password: String): String{
        var logged: String = ""
        val colection = Firebase.firestore.collection("user")

        Log.v("TESTE2", "TESTANDO")

        colection
            .whereEqualTo("email" , email)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    for (document in it.result){
                        val user = document.toObject(User::class.java)
                        Log.v("TESTE3", "NAME; ${user.name} - PASSWORD: ${user.password}")
                    }
                }

            }

        return logged
    }



}