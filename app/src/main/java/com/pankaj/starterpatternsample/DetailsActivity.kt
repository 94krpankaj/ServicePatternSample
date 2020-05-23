package com.pankaj.starterpatternsample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    companion object{
        fun getDetailsActivityIntent(context: Context,money:String){
            context.startActivity(Intent( context,DetailsActivity::class.java)
                .putExtra("money",money))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent = intent
        textView.text ="Hi bro!! Thanks for sending me " +intent.getStringExtra("money")+" $"
    }
}
