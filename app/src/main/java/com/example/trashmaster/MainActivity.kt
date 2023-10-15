package com.example.trashmaster

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle:ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout:DrawerLayout=findViewById(R.id.drawer)
        val navView:NavigationView=findViewById(R.id.nav_view)

        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)


        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.img)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.acc->Toast.makeText(applicationContext,"Clicked",Toast.LENGTH_SHORT).show()
                R.id.rew->Toast.makeText(applicationContext,"Clicked",Toast.LENGTH_SHORT).show()
                R.id.lang->Toast.makeText(applicationContext,"Clicked",Toast.LENGTH_SHORT).show()
                R.id.coll->Toast.makeText(applicationContext,"Clicked",Toast.LENGTH_SHORT).show()
                R.id.supp->Toast.makeText(applicationContext,"Clicked",Toast.LENGTH_SHORT).show()
                R.id.About->Toast.makeText(applicationContext,"Clicked",Toast.LENGTH_SHORT).show()
            }
            true
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bar,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
        {
            return true
        }
       else if(item.itemId==R.id.notifiy)
        {
           Toast.makeText(this,"notified",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

}