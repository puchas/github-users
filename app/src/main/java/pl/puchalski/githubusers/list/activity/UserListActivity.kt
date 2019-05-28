package pl.puchalski.githubusers.list.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.puchalski.githubusers.R

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
    }
}