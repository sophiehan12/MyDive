package com.example.mydive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //프로필 프레그먼트에 데이터 보내기
        var email = intent.getStringExtra("email")
        //Toast.makeText(applicationContext,email, Toast.LENGTH_SHORT).show()
        //if (email != null) {
        //    Log.d("tag", email)
        //}

        //var mContext : FragmentActivity = applicationContext
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment1 = FragmentProfile()
        val bundle = Bundle()

        bundle.putString("Email",email)
        fragment1.setArguments(bundle)
        transaction.replace(R.id.container, fragment1)
        transaction.commit()



        //메뉴 프래그먼트
        with(supportFragmentManager.beginTransaction()){ //맨처음에 보일 프래그먼트
            val fragment_search = FragmentSearch()
            replace(R.id.container, fragment_search)
            commit()
        }
        bottom_navigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.tab_search -> {
                    //Toast.makeText(applicationContext, " 프레그먼트", Toast.LENGTH_SHORT).show()
                    with(supportFragmentManager.beginTransaction()){
                        val fragment_search = FragmentSearch()
                        replace(R.id.container, fragment_search)
                        commit()
                    }
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.tab_log->{
                    with(supportFragmentManager.beginTransaction()){
                        val fragment_log = FragmentLog()
                        replace(R.id.container, fragment_log)
                        commit()
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.tab_profile->{
                    with(supportFragmentManager.beginTransaction()){
                        val fragment_profile = FragmentProfile()
                        replace(R.id.container, fragment_profile)
                        commit()
                    }
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }
}