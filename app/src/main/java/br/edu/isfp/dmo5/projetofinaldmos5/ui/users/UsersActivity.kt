package br.edu.isfp.dmo5.projetofinaldmos5.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.isfp.dmo5.projetofinaldmos5.R
import br.edu.isfp.dmo5.projetofinaldmos5.databinding.ActivityUsersBinding
import br.edu.isfp.dmo5.projetofinaldmos5.ui.adapter.UserAdapter
import br.edu.isfp.dmo5.projetofinaldmos5.util.Constant

class UsersActivity : AppCompatActivity() {

    lateinit var binding: ActivityUsersBinding
    lateinit var viewModel: UserViewModel
    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        handleBundle()
        setUpRecyclerView()
        setObservers()

    }



    private fun setObservers(){
        viewModel.users.observe(this, Observer { user ->
            adapter.submitDataset(user)
            adapter.notifyDataSetChanged()
        })
    }

    private fun setUpRecyclerView(){
        binding.recyclerUsers.layoutManager = LinearLayoutManager(this)
        binding.recyclerUsers.adapter = adapter
    }

    private fun handleBundle(){
        val extras = intent.extras
        if (extras != null){
            val user = extras.getString(Constant.KEY_LOGGEDUSER)
            binding.textUser.text = user

        }
    }

}