 package com.zetzed.livedio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class MenuItemAdapter : RecyclerView.Adapter<MenuItemAdapter.MenuItemAdapterViewHolder>(){

    //Cria Variavel que Acessa arquivo MenuItemModel.kt
    private val list = mutableListOf<MenuItemModel>() //Atributo de classe...Pega de classe  MenuItemModel

    //Declara o Layout de cada item da ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)//Pega o contexto a partir do parent.".inflate" = criar micro layout pro adapter
        return MenuItemAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuItemAdapterViewHolder, position: Int) {
        holder.iniciaViews(list[position]) //Obtem a lista que está no "MenuItemModel.kt" através da fun "iniciaViews" e passa a posição da lista.
    }


    // getItemCount = Verifica a quantidade de Itens de cada ViewHolder
    override fun getItemCount(): Int {
        return list.size
    }

    //Cria função setItensList que limpa a lista e adiciona tudo que tem de novo..para não duplicar itens
    fun setItensList(Lista: List<MenuItemModel>){
        this.list.clear() //Zera a lista
        this.list.addAll(list) //Adi    ciona tudo
    }

    //Cria subclasse
    class MenuItemAdapterViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){
        fun iniciaViews(item: MenuItemModel){ //Cria uma função e passa como parâmetro item a classe "MenuItem Model" que está no arquivo "MenuItemModel.kt".
            //item.titulo //Pega val "titulo" da classe "MenuItemModel" no arquivo "MenuItemModel.kt"
        }
    }
}