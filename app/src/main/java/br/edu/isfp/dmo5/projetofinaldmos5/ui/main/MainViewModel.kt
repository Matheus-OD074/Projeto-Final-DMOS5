package br.edu.isfp.dmo5.projetofinaldmos5.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import br.edu.isfp.dmo5.projetofinaldmos5.data.model.User
import br.edu.isfp.dmo5.projetofinaldmos5.data.repository.UserRepository
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class MainViewModel: ViewModel() {

    private val repository = UserRepository()

    fun login(email: String, password: String) : Boolean{

        var user: User? = null
        var logged = false
        val colection = Firebase.firestore.collection("user")

        colection.document(email)
            .get()
            .addOnSuccessListener {
                user = it.toObject(User::class.java)
                Log.v("PASSWORD", user!!.password)
                if (user != null){
                    if (user!!.password == password){
                       logged = true
                    }

                }
            }
        Log.v("SENHA", logged.toString())
        return logged
    }



}