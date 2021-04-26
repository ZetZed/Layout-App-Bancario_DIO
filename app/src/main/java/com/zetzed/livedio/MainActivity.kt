package com.zetzed.livedio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvList: RecyclerView //lateinit = cria atributo de classe sem necessidade de inicializar ..Informando que pode ser null
    private var adapter = MenuItemAdapter() //Pega a classe MenuItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Funções que inicia no onCreate
        iniciaViews()
        setItemsLista()
    }

    private fun iniciaViews(){
        rvList = findViewById(R.id.rv_list)
        rvList.adapter = adapter //Pegando a classe MenuItem Adapter e passando o adapter do id "rv_list"
        rvList.layoutManager = GridLayoutManager(this, 2)   // Definir orientação do View, como ele vai se organizar...
    }


    //Pega RecyclerView e seta lista para Recycler
    private fun setItemsLista() {
        adapter.setItensList( //Chama função "setItensList" que está em "MenuItemAdapter.kt"
                arrayListOf( //Cria lista fictícia...
                        MenuItemModel(
                                "cartões"
                        ),
                        MenuItemModel(
                                "meus comprovantes"
                        ),
                        MenuItemModel(
                                "investimentos"
                        ),
                        MenuItemModel(
                                "portabilidade de salário"
                        ),
                        MenuItemModel(
                                "poupança"
                        ),
                        MenuItemModel(
                                "crédito"
                        )
                )
        )

    }
}