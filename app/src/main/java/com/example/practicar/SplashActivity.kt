package com.example.practicar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pruebatecnica.R
import com.example.pruebatecnica.databinding.ActivityDataStoreBinding
import com.example.pruebatecnica.databinding.ActivityDetailStoreBinding
import com.example.pruebatecnica.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Thread.sleep(1000)


//        val content: View = findViewById(android.R.id.content)
//        content.viewTreeObserver.addOnPreDrawListener(
//            object: ViewTreeObserver.OnPreDrawListener{
//                override fun onPreDraw(): Boolean {
//                    // Check if the initial data is ready.
//                    return if (viewModel.isReady){
//                        //The content is ready; start drawing.
//                        content.viewTreeObserver.removeOnPreDrawListener(this)
//                        true
//                    } else {
//                        //The content is not ready; suspend.
//                        false
//                    }
//                }
//            }
//        )
//

        splash.setKeepOnScreenCondition {
            //  true --> que nunca se vaya el splash
            false  //que muestre el spash y cuando se cargue se quite
        }
        startActivity(Intent(this, DataStore::class.java))
        finish()
        //Para hacer animaciones es con puro "VECTOR ANIMATION"
        //y solo se veran visible en android-12 por lo tanto api 31--> drawable-v31 si es menor se tomara la imagen por lo tanto es importante que se llamen igual
    }


}