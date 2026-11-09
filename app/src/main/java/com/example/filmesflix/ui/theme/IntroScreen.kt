package com.example.filmesflix.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.filmesflix.components.CustomButton

@Composable
fun IntroScreen(onFinishIntro: () -> Unit) {
    var currentPage by remember { mutableStateOf(0) }

    val titles = listOf("Explore Filmes", "Crie sua Lista", "Aproveite Agora!")
    val descriptions = listOf(
        "Descubra os melhores títulos do cinema mundial.",
        "Marque e acompanhe seus filmes favoritos.",
        "Tudo o que você precisa direto no seu bolso."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = titles[currentPage], style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = descriptions[currentPage], style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Página ${currentPage + 1} de 3", style = MaterialTheme.typography.labelMedium)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (currentPage > 0) {
                CustomButton(
                    text = "Voltar",
                    onClick = { currentPage-- }
                )
            } else {
                Spacer(modifier = Modifier.width(1.dp))
            }

            if (currentPage < 2) {
                CustomButton(
                    text = "Avançar",
                    onClick = { currentPage++ }
                )
            } else {
                CustomButton(
                    text = "Começar",
                    onClick = onFinishIntro
                )
            }
        }
    }
}