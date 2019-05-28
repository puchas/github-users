package pl.puchalski.githubusers.list.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_user_list.*
import pl.puchalski.githubusers.R
import pl.puchalski.githubusers.list.adapter.UserListAdapter
import pl.puchalski.githubusers.list.viewmodel.UserListViewModel

class UserListActivity : AppCompatActivity() {

    private lateinit var adapter: UserListAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(UserListViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        prepareAdapter()

        viewModel.usersData.observe(this, Observer {
            adapter.updateUsers(it)
        })

        search_text.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    p0?.let {
                        viewModel.search(it.toString())
                    }
                }

            }
        )
    }

    private fun prepareAdapter() {
        adapter = UserListAdapter(listOf())
        user_list.adapter = adapter
        user_list.layoutManager = LinearLayoutManager(this)
    }
}