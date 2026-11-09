package com.example.filmesflix.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.filmesflix.components.AppTopBar
import com.example.filmesflix.components.ItemCard
import com.example.filmesflix.components.SearchField
import com.example.filmesflix.data.MockData

@Composable
fun HomeScreen(onItemClick: (Int) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    var isAscending by remember { mutableStateOf(true) }

    val filteredList = MockData.listaItens
        .filter { it.titulo.contains(searchQuery, ignoreCase = true) }
        .let { list ->
            if (isAscending) list.sortedBy { it.titulo }
            else list.sortedByDescending { it.titulo }
        }

    Scaffold(
        topBar = { AppTopBar(title = "Início - FilmesFlix") },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                searchQuery = ""
                isAscending = true
            }) {
                Text(text = "X")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            SearchField(query = searchQuery, onQueryChange = { searchQuery = it })

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { isAscending = !isAscending }) {
                    Text(if (isAscending) "Ordem: A-Z" else "Ordem: Z-A")
                }
                Text(
                    text = "${filteredList.size} itens encontrados",
                    modifier = Modifier.padding(top = 12.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(filteredList) { item ->
                    ItemCard(item = item, onItemClick = onItemClick)
                }
            }
        }
    }
}