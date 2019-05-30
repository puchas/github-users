package pl.puchalski.githubusers.list.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_user_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import pl.puchalski.githubusers.R
import pl.puchalski.githubusers.details.activity.UserDetailsActivity
import pl.puchalski.githubusers.list.adapter.UserListAdapter
import pl.puchalski.githubusers.list.viewmodel.UserListViewModel

class UserListActivity : AppCompatActivity() {

    private lateinit var adapter: UserListAdapter
    private val viewModel: UserListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        title = "Wyszukaj użytkownika"

        prepareAdapter()

        viewModel.usersData.observe(this, Observer {
            adapter.updateUsers(it)
        })

        search_button.setOnClickListener {
            viewModel.search(search_text.text.toString())
        }
    }

    private fun prepareAdapter() {
        adapter = UserListAdapter(listOf()) { onUserClick(it) }
        user_list.adapter = adapter
        user_list.layoutManager = LinearLayoutManager(this)
    }

    private fun onUserClick(login: String) {
        UserDetailsActivity.startForUser(this, login)
    }
}