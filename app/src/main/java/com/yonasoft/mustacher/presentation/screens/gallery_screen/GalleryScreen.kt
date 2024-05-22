package com.yonasoft.mustacher.presentation.screens.gallery_screen

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable

@Composable
fun GalleryScreen(){

    LazyVerticalGrid(columns =GridCells.Fixed(3), content = {
        items(count = 0){

        }
    })
}