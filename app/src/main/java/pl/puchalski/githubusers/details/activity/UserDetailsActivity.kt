package pl.puchalski.githubusers.details.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_details.*
import org.koin.android.viewmodel.ext.android.viewModel
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

    private val viewModel: UserDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val login = restoreLogin()
        title = "Szczegóły użytkownika $login"

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
        Picasso.get().load(userDetails.avatar_url).into(avatar)
        login.text = userDetails.login
        setOptionalField(name, userDetails.name)
        setOptionalField(blog_link, userDetails.blog)
        setOptionalField(location, userDetails.location)
    }

    private fun setOptionalField(textView: TextView, string: String?) {
        if (string.isNullOrBlank()) {
            textView.visibility = View.GONE
        } else {
            textView.apply {
                visibility = View.VISIBLE
                text = string
            }
        }
    }
}