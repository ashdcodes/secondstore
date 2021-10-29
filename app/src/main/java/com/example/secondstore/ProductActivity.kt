package com.example.secondstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
@Suppress("DEPRECATION")
class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val pName: TextView = findViewById(R.id.prName)
        val pCat:TextView=findViewById(R.id.prCategory)
        val pPr: TextView=findViewById(R.id.prPrice)
        val pCon:TextView=findViewById(R.id.prCondition)
        val pImg:ImageView=findViewById(R.id.prImage)
        val pDesc:TextView=findViewById(R.id.prDescription)

        val bundle:Bundle?=intent.extras
        val prodName=bundle!!.getString("prodName")
        val prodCategory= bundle.getString("prodCategory")
        val prodPrice=bundle.getString("prodPrice")
        val prodCondition=bundle.getString("prodCondition")
        val prodDesc=bundle.getString("prodDesc")
        val imageId= bundle.getInt("imageId")

        pName.text=prodName
        pCat.text=prodCategory
        pPr.text=prodPrice
        pCon.text=prodCondition
        pImg.setImageResource(imageId)
        pDesc.text=prodDesc
    }
}