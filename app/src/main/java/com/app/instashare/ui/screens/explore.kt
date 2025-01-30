package com.app.instashare.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.app.instashare.R
import com.app.instashare.viewModels.PhotoViewModel

@Composable
fun ExploreView() {
    val searchField = remember { mutableStateOf(TextFieldValue("")) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()

    ) {
        item {

            Column {
                Box(
                    modifier = Modifier.padding(all = 10.dp)
                ) {
                    TextField(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.Transparent,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        shape = RoundedCornerShape(10.dp),
                        textStyle = TextStyle(
                            lineHeight = 15.sp
                        ),
                        value = searchField.value,
                        placeholder = {
                            Text(text = "Search", fontSize = 15.sp)
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        onValueChange = {
                            searchField.value = it
                        },
                        leadingIcon = {
                            Image(
                                painter = painterResource(R.drawable.search_outlined),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(color = Color.Gray),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                }
                Box(modifier = Modifier.height(10.dp))
            }
        }
        item {
            PhotosListView(Modifier.fillParentMaxSize())
        }

    }
}

@Composable
fun PhotosListView(modifier: Modifier, viewModel: PhotoViewModel = hiltViewModel()) {



    val photos = remember { mutableStateOf(viewModel.photos) }
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalItemSpacing = 2.dp
    ) {
        items(photos.value.value.gifs) { item ->
            AsyncImage(
                model = "https://random-d.uk/api/$item",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(color = Color.LightGray),
            )
        }
    }
}