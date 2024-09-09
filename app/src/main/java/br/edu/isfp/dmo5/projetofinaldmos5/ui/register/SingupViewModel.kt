package br.edu.isfp.dmo5.projetofinaldmos5.ui.register

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.isfp.dmo5.projetofinaldmos5.data.model.User
import br.edu.isfp.dmo5.projetofinaldmos5.data.repository.UserRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SingupViewModel: ViewModel() {

    val repository = UserRepository()

    private val _inserted = MutableLiveData<Boolean>()
    val inserted: LiveData<Boolean> = _inserted

    private val _allRinserted = MutableLiveData<Boolean>()
    val allRinserted: LiveData<Boolean> = _allRinserted


    fun insert(email: String, password: String, name: String){

        val colection = Firebase.firestore.collection(email)

        if(colection.get() == null){
            val user = User(email, password, name)
            repository.insert(user, { result ->
                _inserted.value = result
            })
        }else{
            _allRinserted.value = true
        }


    }

}