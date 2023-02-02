package com.example.petadoptmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.petadoptmobile.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnanjing : Button
    private lateinit var btnkucing : Button
    private lateinit var btnburung : Button
    private lateinit var btnikan : Button
    val fraghome : Fragment = HomeFragment()
    val fragcart : Fragment = CartFragment()
    val fragprof : Fragment = ProfileFragment()
    val fm : FragmentManager = supportFragmentManager
    var active : Fragment = fraghome

    private lateinit var menu : Menu
    private lateinit var menuItem : MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnanjing = findViewById(R.id.btn_anjing)
        btnkucing = findViewById(R.id.bt_kucing)
        btnburung = findViewById(R.id.btn_burung)
        btnikan = findViewById(R.id.btn_ikan)


        btnanjing.setOnClickListener(this)
        btnkucing.setOnClickListener(this)
        btnburung.setOnClickListener(this)
        btnikan.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_anjing ->{
                val intenanjing =Intent(this@Home,anjing1::class.java)
                startActivity(intenanjing)
            }
        }

        when(v.id){
            R.id.bt_kucing ->{
                val intenkucing =Intent(this@Home,kucing::class.java)
                startActivity(intenkucing)
            }
        }


        when(v.id){
            R.id.btn_burung ->{
                val intenburung =Intent(this@Home,burung::class.java)
                startActivity(intenburung)
            }
        }


        when(v.id){
            R.id.btn_ikan ->{
                val intenikan =Intent(this@Home,ikan::class.java)
                startActivity(intenikan)
            }
        }

        setUpNaviBottom()

    }

    private fun setUpNaviBottom() {
        fm.beginTransaction().add(R.id.navi_content,fraghome).show(fraghome).commit()
        fm.beginTransaction().add(R.id.navi_content,fragcart).show(fragcart).commit()

        menu = btn_navi_view.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        btn_navi_view.setOnNavigationItemSelectedListener {
            item->
            when (item.itemId){
                R.id.navi_home ->{
                    callFrag(0,fraghome)
                }
                R.id.navi_cart -> {
                    callFrag(1, fragcart)
                }
                R.id.navi_profile -> {
                    callFrag(2, fragprof)
                }
            }
            false
        }
    }

    private fun callFrag(i:Int, fragment: Fragment)  {
        menuItem = menu.getItem(i)
        menuItem.isChecked = true

        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }


}

