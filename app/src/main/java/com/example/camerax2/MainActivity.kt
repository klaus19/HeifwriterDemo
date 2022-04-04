package com.example.camerax2



import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.heifwriter.HeifWriter
import com.example.camerax2.databinding.ActivityMainBinding
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClick.setOnClickListener {
            conversionImage()
        }


    }

    private fun conversionImage() {

        //Step1 : Loading jpeg or jpg image and converting it into BitMap

        val bitMap = BitmapFactory.decodeStream(assets.open("white.jpg"))
        val imageHeight = bitMap.height
        val imageWidth = bitMap.width
        val destination = "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)}/white.heic"
        Log.d("convert",destination)


        //Step 2 Create Heic writer instance and convert

        try {
            HeifWriter.Builder(destination,imageHeight,imageWidth,HeifWriter.INPUT_MODE_BITMAP)
                .setQuality(90)
                .build().run {
                    start()
                    addBitmap(bitMap) //add Bitmap if writer is using Bitmap
                    stop(0)  // 0 mean infinitely running
                    close() //close after use
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}