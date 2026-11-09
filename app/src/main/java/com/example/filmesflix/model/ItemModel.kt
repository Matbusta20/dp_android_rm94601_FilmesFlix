package com.example.filmesflix.model

data class ItemModel(
    val id: Int,
    val titulo: String,
    val descricao: String,
    val categoria: String,
    val imagemResId: Int,
    val infoAdicional1: String,
    val infoAdicional2: String,
    val infoAdicional3: String,
    var isFavorito: Boolean = false
)