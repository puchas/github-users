package pl.puchalski.githubusers.list.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_user_list.*
import pl.puchalski.githubusers.R
import pl.puchalski.githubusers.list.adapter.UserListAdapter

class UserListActivity : AppCompatActivity() {

    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        prepareAdapter()
    }

    private fun prepareAdapter() {
        adapter = UserListAdapter(listOf())
        user_list.adapter = adapter
        user_list.layoutManager = LinearLayoutManager(this)
    }
}