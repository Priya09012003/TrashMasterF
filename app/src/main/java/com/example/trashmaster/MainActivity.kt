package com.example.trashmaster

import android.Manifest
import android.annotation.SuppressLint
import android.app.Instrumentation.ActivityResult
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.media.Image
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.TextureView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var toggle:ActionBarDrawerToggle
    lateinit var cameraBtn:ImageButton
    lateinit var drawerLayout: DrawerLayout
    val REQUEST_IMAGE_CAPTURE=100
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         drawerLayout=findViewById(R.id.drawer)
        val navView:NavigationView=findViewById(R.id.nav_view)

        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)


        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.img)
        navView.setNavigationItemSelectedListener {
            it.isChecked=true
            when(it.itemId){
                R.id.home->{
                    it.isChecked=true
                    val intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.acc->Replace(myAccount(),"My Account")
                R.id.rew->Replace(Rewards(),it.title.toString())
                R.id.coll->Replace(totalCollection(),"Collection")
                R.id.supp->Replace(Support(),it.title.toString())
                R.id.Report->Replace(Report(),it.title.toString())
            }
            true
        }

        //got to activity set image
//        val cam=findViewById<ImageButton>(R.id.cambtn)
//        imageUri=createImageUri()!!
//        cam.setOnClickListener {
//           val intent=Intent(this,Image_Upload::class.java)
//            intent.putExtra("img",imageUri)
//            startActivity(intent)
//        }

//        if(ContextCompat.checkSelfPermission(applicationContext,Manifest.permission.C)==PackageManager.){
//
//        }
        get_permissions()
        cameraBtn=findViewById(R.id.cambtn)

        cameraBtn.setOnClickListener {
            val takePictureIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            try {
                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE)
            }catch(e:ActivityNotFoundException)
            {
                Toast.makeText(this,"ERRor",Toast.LENGTH_SHORT).show()
            }
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode== RESULT_OK)
        {
            val imageBitmap=data?.extras?.get("data") as Bitmap
            val intent1=Intent(this,Image_Upload::class.java)
            intent1.putExtra("bit",imageBitmap)
            startActivity(intent1)
           // image.setImageBitmap(imageBitmap)
        }
        else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }






    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bar,menu)
        return true
    }
    //select item
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
        {
            return true
        }
       else if(item.itemId==R.id.notifiy)
        {
            Replace(Notfiction(),"Notification")
        }
        return super.onOptionsItemSelected(item)
    }
//camera
    @RequiresApi(Build.VERSION_CODES.M)
    fun get_permissions()
    {
        var permissionsLst = mutableListOf<String>()

        if(checkSelfPermission(android.Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED)permissionsLst.add(android.Manifest.permission.CAMERA)
        if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)permissionsLst.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)permissionsLst.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(permissionsLst.size>0)
        {
            requestPermissions(permissionsLst.toTypedArray(),101)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        grantResults.forEach {
            if(it!=PackageManager.PERMISSION_GRANTED)
            {
                get_permissions()
            }
        }
    }

    private fun Replace(fragment: Fragment,title:String)
    {
    val fragmentManager=supportFragmentManager
    val transcation =fragmentManager.beginTransaction()
        transcation.replace(R.id.frame,fragment)
        transcation.commit()
        drawerLayout.closeDrawers()
        setTitle(title)

    }
    //create image uri
//    private fun createImageUri(): Uri?
//    {
//        val image= File(applicationContext.filesDir,"camera_photos.png")
//        return FileProvider.getUriForFile(applicationContext,"com.example.trashmaster.fileProvider",image)
//    }

}