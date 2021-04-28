package com.zetzed.livedio

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_menu.*

class MainActivity : AppCompatActivity() {


    private lateinit var rvList: RecyclerView //lateinit = cria atributo de classe sem necessidade de inicializar ..Informando que pode ser null
    private var adapter = MenuItemAdapter() //Pega a classe MenuItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //PHOTO(BIBLIOTECA DE FOTOS) -> Verifica Versão do dispositivo e pede permissão se necessário.
        photo.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){//Verifica se o Dispositivo tem a versão igual ou maior q a MarshMallow.
                // PEDE PERMISSÃO
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) //Verifica se está permitido ou não acesso ao armazenamento do dispositivo.
                == PackageManager.PERMISSION_DENIED) { //Se a permissão estiver negada
                    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)//Abre alerta de pedido de permissão de acesso ao armazenamento do dispositivo
                    requestPermissions(permission, PERMISSION_CODE) // Pede permissão de acesso.
                }
                else{ //Se o dispositivo for maior= que Marshmallow e JÁ ESTIVER PERMITIDO.
                    pickImageFromGallery()//Pega Imagens da Galeria.
                }
            }
            else{ //Se a versão do dispositivo for menor que Marshmallow..NÃO PRECISA PEDIR PERMISSÃO
                pickImageFromGallery() //Pega Imagens da Galeria.
            }
        }

        //Funções que inicia no onCreate
        iniciaViews()
        setItemsLista()
    }

    //PHOTO(BIBLIOTECA DE FOTOS) -> Função para pegar foto da galeria
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK) //Ação de pegar a foto.
        intent.type = "image/*" //Pode pegar tudo que for do tipo imagem.
        startActivityForResult(intent, PHOTO_CODE) //Verifica se é o código de PHOTO_CODE, para ter a resposta certa. Para separar a Activity que está trabalhando.
    }

    //PHOTO(BIBLIOTECA DE FOTOS) -> Verifica se a Activity é a mesma solicitada e muda a foto.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PHOTO_CODE) {//verifica se resultCode é igual a Activity OK
            photo.setImageURI(data?.data) //Coloca a nova imagem através do id photo(da activity_main).
        }
    }

    //PHOTO(BIBLIOTECA DE FOTOS) -> Cria objeto que vair conter os valores de permissão
    companion object{
        private val PERMISSION_CODE = 1000 //Verifica se o codigo de permissão é o mesmo do pedido.
        private val PHOTO_CODE = 1001 //Verifica as permissões. Se a intent que está fazendo tem referência a esta intent.
    }

    //PHOTO(BIBLIOTECA DE FOTOS) -> Tratamento da permissão
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){ // quando o código de requisição(requestCode)...
            PERMISSION_CODE -> {// For igual ao código de permissão(PERMISSION_CODE)...
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { //se o tamanho do grantResults não for nulo(maior que zero) e o grantResults na posição 0 da array for igual a permissão Garantida.
                    pickImageFromGallery()
                }
                else{ //Se usuário negar a permissão
                    Toast.makeText(this,"Permissão Negada", Toast.LENGTH_SHORT).show()
                }
            }
        }
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