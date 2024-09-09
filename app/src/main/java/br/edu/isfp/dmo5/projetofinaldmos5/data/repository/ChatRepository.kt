package br.edu.isfp.dmo5.projetofinaldmos5.data.repository

import android.util.Log
import br.edu.isfp.dmo5.projetofinaldmos5.data.model.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore

class ChatRepository {
    companion object{
        const val TAG = "DMO"
        const val COLLECTION = "chat"
        const val ATTR_USERSENDER = "userSender"
        const val ATTR_MSG = "msg"
        const val ATTR_USERRECIVER = "userReceiver"
    }
    private val database = Firebase.firestore

    fun findAll(callback: (List<User>) -> Unit){
        database.collection(COLLECTION)
            .orderBy(ATTR_USERRECIVER, Query.Direction.ASCENDING)
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

    fun insert(userSender: String, msg: String, userReceiver: String, callback: (Boolean) -> Unit){

        val _user = hashMapOf(
            ATTR_USERSENDER to userSender,
            ATTR_USERRECIVER to userReceiver,
            ATTR_MSG to msg
        )

        database.collection(COLLECTION)
            .document(userSender)
            .set(_user)
            .addOnSuccessListener{
                callback(true)
            }
            .addOnFailureListener{
                callback(false)
            }
    }
}