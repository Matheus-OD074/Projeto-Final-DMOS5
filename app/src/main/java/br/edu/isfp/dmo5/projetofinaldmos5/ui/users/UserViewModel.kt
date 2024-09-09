package br.edu.isfp.dmo5.projetofinaldmos5.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.isfp.dmo5.projetofinaldmos5.data.model.User
import br.edu.isfp.dmo5.projetofinaldmos5.data.repository.UserRepository
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserViewModel: ViewModel() {
    private val repository = UserRepository()

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _inserted = MutableLiveData<Boolean>()
    val inserted: LiveData<Boolean> = _inserted

    private val _allRinserted = MutableLiveData<Boolean>()
    val allRinserted: LiveData<Boolean> = _allRinserted

    init {
        loadUsers()
    }

    fun insert(email: String, password: String, name: String){

        val colection = Firebase.firestore.collection("user")

        colection.document(email)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val document: DocumentSnapshot = it.result
                    if(document.exists()){
                        _allRinserted.value = true
                    }else{
                        val user = User(email, password, name)
                        repository.insert(user, { result ->
                            _inserted.value = result
                        })
                    }
                }
            }
    }

    private fun loadUsers(){
        repository.findAll { list -> _users.value = list }
    }
}