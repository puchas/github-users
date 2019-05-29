package pl.puchalski.githubusers.details.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_user_details.*
import pl.puchalski.githubusers.R
import pl.puchalski.githubusers.details.viewmodel.UserDetailsViewModel
import pl.puchalski.githubusers.model.UserDetails

class UserDetailsActivity : AppCompatActivity() {

    companion object {

        private const val LOGIN_KEY = "login_key"

        fun startForUser(context: Context, login: String) {
            val intent = Intent(context, UserDetailsActivity::class.java)
            intent.putExtra(LOGIN_KEY, login)
            context.startActivity(intent)
        }

    }

    private val viewModel by lazy { ViewModelProviders.of(this).get(UserDetailsViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val login = restoreLogin()

        viewModel.user.observe(this, Observer { showUserDetails(it) })

        viewModel.getUserDetails(login)
    }

    private fun restoreLogin(): String {
        val login = intent.extras?.getString(LOGIN_KEY)

        if (null == login) {
            throw IllegalArgumentException("Use method startForUser to start UserDetailsActivity")
        } else {
            return login
        }
    }

    private fun showUserDetails(userDetails: UserDetails) {

        login.text = userDetails.login

    }
}