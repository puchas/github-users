package pl.puchalski.githubusers.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user.view.*
import pl.puchalski.githubusers.R
import pl.puchalski.githubusers.model.User

class UserListAdapter(private var users: List<User>) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_user,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.itemView.login.text = user.login
    }

    override fun getItemCount(): Int = users.size

    fun updateUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

}