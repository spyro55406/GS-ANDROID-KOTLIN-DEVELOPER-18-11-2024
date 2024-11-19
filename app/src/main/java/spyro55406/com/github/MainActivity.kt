package spyro55406.com.github

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import spyro55406.com.github.GuilhermeMiguel_RM94599.R
import spyro55406.com.github.viewmodel.ItemsAdapter
import spyro55406.com.github.viewmodel.ItemsViewModel
import spyro55406.com.github.viewmodel.ItemsViewModelFactory



class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Dicas Ecologicas"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val itemsAdapter = ItemsAdapter { item ->
            viewModel.removeItem(item)
        }

        recyclerView.adapter = itemsAdapter


        val button = findViewById<Button>(R.id.button)
        val editTextTitulo = findViewById<EditText>(R.id.editTextTitulo)
        val editTextDesc = findViewById<EditText>(R.id.editTextDesc)

        button.setOnClickListener {
            if (editTextTitulo.text.isEmpty()) {
                editTextTitulo.error = "Coloque o Titulo"
                return@setOnClickListener
            }

            if (editTextDesc.text.isEmpty()) {
                editTextDesc.error = "Coloque a Descrição"
                return@setOnClickListener
            }

            viewModel.addItem(editTextTitulo.text.toString(), editTextDesc.text.toString())
            editTextTitulo.text.clear()
            editTextDesc.text.clear()

        }

        val viewModelFactory = ItemsViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)

        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }
    }
}