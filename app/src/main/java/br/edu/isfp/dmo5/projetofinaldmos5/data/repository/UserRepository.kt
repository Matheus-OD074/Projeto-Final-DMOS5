package br.edu.isfp.dmo5.projetofinaldmos5.data.repository

import android.util.Log
import br.edu.isfp.dmo5.projetofinaldmos5.data.model.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore

class UserRepository {
    companion object{
        const val TAG = "DMO"
        const val COLLECTION = "user"
        const val ATTR_EMAIL = "email"
        const val ATTR_PASSWORD = "password"
        const val ATTR_NAME = "name"
    }
    private val database = Firebase.firestore

    fun findAll(callback: (List<User>) -> Unit){
        database.collection(COLLECTION)
            .orderBy(ATTR_EMAIL, Query.Direction.ASCENDING)
            .addSnapshotListener(){ querySnapshot, exception ->
                if (exception != null){
                    Log.e(TAG, "Listen fail.")
                    callback(emptyList())
                    return@addSnapshotListener
                }

                if (querySnapshot != null){
                    val list = querySnapshot.toObjects(User::class.java)
                    callback(list)
                }else{
                    callback(emptyList())
                }

            }
    }

    fun insert(user: User, callback: (Boolean) -> Unit){

        val _user = hashMapOf(
            ATTR_NAME to user.name,
            ATTR_PASSWORD to user.password
        )

        database.collection(COLLECTION)
            .document(user.email)
            .set(_user)
            .addOnSuccessListener{
                callback(true)
            }
            .addOnFailureListener{
                callback(false)
            }
    }
}