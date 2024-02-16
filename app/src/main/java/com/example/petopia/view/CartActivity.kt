package com.example.petopia.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room.databaseBuilder
import com.example.petopia.R
import com.example.petopia.adapter.cartAdapter
import com.example.petopia.db.AppDatabase
import com.example.petopia.db.Item
import com.example.petopia.db.ItemDAO
import com.example.petopia.db.Space
import java.security.AccessController.getContext

class CartActivity : AppCompatActivity() {


    lateinit var cartRecyclerView : RecyclerView
    lateinit var cartItemTotalPrice : TextView
    lateinit var cartTotalPrice : TextView
    lateinit var cartDeliveryCharge : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cartItemTotalPrice = findViewById(R.id.cartItemTotalPrice)
        cartTotalPrice = findViewById(R.id.cartTotalPrice)
        cartDeliveryCharge = findViewById(R.id.cartDeliveryCharge)
        cartRecyclerView = findViewById(R.id.cartRecyclerView)

        getRoomData()

    }


    private fun getRoomData() {
        val db: AppDatabase = databaseBuilder(this, AppDatabase::class.java, "cart_db").allowMainThreadQueries().build()
        val itemDAO: ItemDAO = db.itemDAO()


        cartRecyclerView.layoutManager = LinearLayoutManager(this)

        val items: List<Item> = itemDAO.getAll()

        val cartAdapter: cartAdapter = cartAdapter(items, cartItemTotalPrice, cartTotalPrice, cartDeliveryCharge)
        cartRecyclerView.adapter = cartAdapter
        cartRecyclerView.addItemDecoration(Space(5))


        var sum = 0
        var total = 0
        val delivery = 110
        for (i in items.indices) {
            sum = sum + (items[i].getPrice().toInt() * items[i].getQuantity())
        }
        total += sum + delivery

        cartItemTotalPrice.text = "$sum tk"

        if (sum == 0) {
            cartTotalPrice.text = "0 tk"
            cartDeliveryCharge.text = "0 tk"
        } else {
            cartTotalPrice.text = "$total tk"
            cartDeliveryCharge.text = "$delivery tk"
        }
    }


}