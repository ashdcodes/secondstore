package com.example.secondstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
//import android.widget.SearchView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

private lateinit var newRecyclerView: RecyclerView
private lateinit var newArrayList: ArrayList<Product>
private lateinit var tempArrayList:ArrayList<Product>
lateinit var prodDesc:Array<String>
lateinit var prodName:Array<String>
lateinit var prodCategory:Array<String>
lateinit var prodPrice:Array<String>
lateinit var prodCondition:Array<String>
lateinit var imageId:Array<Int>


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prodName= arrayOf(
            "Iphone 11",
            "Macbook",
            "PowerBank",
            "Iphone SE",
            "Mac Mini",
            "Watch 6",
            "Airpods",
            "i58400F",
            "Iphone X",
            "Tomahawk",
            "Rework",
            "Pixel 4",
            "Router",
            "Pixel 5",
            "Screwdriver",
            "Remote",
            "Note 20",
            "Keyboard",
            "Xiaomi 10T",
            "RTX2060"


        )

        prodCategory= arrayOf(
            "MOBILE",
            "LAPTOP",
            "BATTERY",
            "MOBILE",
            "PC",
            "WEARABLE",
            "MUSIC",
            "PC",
            "MOBILE",
            "PC",
            "BOOK",
            "MOBILE",
            "NETWORK",
            "MOBILE",
            "TOOLS",
            "BOOK",
            "MOBILE",
            "PC",
            "MOBILE",
            "PC",
        )
        prodPrice= arrayOf(
            "$600",
            "$999",
            "$150",
            "$400",
            "$650",
            "$699",
            "$500",
            "$399",
            "$499",
            "$299",
            "$199",
            "$399",
            "$499",
            "$699",
            "$120",
            "$199",
            "$899",
            "$149",
            "$600",
            "$799",
        )
        prodCondition= arrayOf(
            "Old",
            "Almost New",
            "New",
            "Old",
            "New",
            "Old",
            "Almost New",
            "Old",
            "Almost New",
            "New",
            "Old",
            "Almost New",
            "New",
            "Old",
            "New",
            "Old",
            "New",
            "Old",
            "Almost New",
            "New",
        )

        prodDesc= arrayOf(
            getString(R.string.prod_a),
            getString(R.string.prod_b),
            getString(R.string.prod_c),
            getString(R.string.prod_d),
            getString(R.string.prod_e),
            getString(R.string.prod_f),


        )
        imageId= arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
        )


        newRecyclerView=findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager=LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList= arrayListOf<Product>()
        tempArrayList= arrayListOf<Product>()
        getUserData()
    }

    private fun getUserData(){
        for(i in prodName.indices){
            val product=Product(prodName[i], prodCategory[i],prodPrice[i], prodCondition[i])
            newArrayList.add(product)
        }
        tempArrayList.addAll(newArrayList)

        val adapter = DataAdapter(tempArrayList)
        newRecyclerView.adapter=adapter
        adapter.setOnItemClickListener(object : DataAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
//                Toast.makeText(this@MainActivity,"You clicked on item no $position",Toast.LENGTH_SHORT).show()
            val intent=Intent(this@MainActivity,ProductActivity::class.java)
                intent.putExtra("prodName", newArrayList[position].productName)
                intent.putExtra("prodCategory", newArrayList[position].productCategory)
                intent.putExtra("prodPrice", newArrayList[position].productPrice)
                intent.putExtra("prodCondition", newArrayList[position].productCondition)
                intent.putExtra("prodDesc", prodDesc[position])
                intent.putExtra("imageId", imageId[position])
                startActivity(intent)
            }

        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
        val item= menu?.findItem(R.id.search_action)
        val searchView= item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                tempArrayList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if(searchText.isNotEmpty()){
                    newArrayList.forEach{
                        if(it.productName.lowercase(Locale.getDefault()).contains(searchText)){
                            tempArrayList.add(it)
                        }
                    }
                    newRecyclerView.adapter!!.notifyDataSetChanged()
                }else{
                    tempArrayList.clear()
                    tempArrayList.addAll(newArrayList)
                    newRecyclerView.adapter!!.notifyDataSetChanged()
                }

                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }


}