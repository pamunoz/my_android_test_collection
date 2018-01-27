package com.pfariasmunoz.firebasech1.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import com.pfariasmunoz.firebasech1.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var messageRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        messageRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error : DatabaseError?) {
                tv_data.text = "Error $error"
            }

            override fun onDataChange(snapshot : DataSnapshot?) {
                tv_data.text = snapshot?.getValue<String>(String::class.java)
            }
        })
        btn_write_data.setOnClickListener { messageRef.setValue("Hello, How are you?") }
    }
}
