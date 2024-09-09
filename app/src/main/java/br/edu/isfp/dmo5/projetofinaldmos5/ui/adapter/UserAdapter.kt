package br.edu.isfp.dmo5.projetofinaldmos5.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.isfp.dmo5.projetofinaldmos5.R
import br.edu.isfp.dmo5.projetofinaldmos5.data.model.User
import br.edu.isfp.dmo5.projetofinaldmos5.databinding.ItemUserBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var dataset: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user,
            parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textUser.text = dataset[position].name
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun submitDataset(dataset: List<User>){
        this.dataset = dataset
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding: ItemUserBinding = ItemUserBinding.bind(view)
    }
}