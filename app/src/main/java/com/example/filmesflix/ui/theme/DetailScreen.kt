package com.example.filmesflix.ui.theme

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.filmesflix.components.AppTopBar
import com.example.filmesflix.model.ItemModel

@Composable
fun DetailScreen(
    item: ItemModel,
    onNavigateBack: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(item.isFavorito) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppTopBar(
                title = item.titulo,
                canNavigateBack = true,
                onNavigateBack = onNavigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = item.imagemResId),
                contentDescription = item.titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = item.titulo, style = MaterialTheme.typography.headlineSmall)
            Text(text = item.categoria, style = MaterialTheme.typography.labelLarge)

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.descricao, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Informações Adicionais", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "• ${item.infoAdicional1}")
                    Text(text = "• ${item.infoAdicional2}")
                    Text(text = "• ${item.infoAdicional3}")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { isFavorite = !isFavorite }) {
                    Text(if (isFavorite) "❤️ Favoritado" else "🤍 Favoritar")
                }

                Button(onClick = {
                    Toast.makeText(context, "Compartilhando: ${item.titulo}", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Compartilhar")
                }
            }
        }
    }
}