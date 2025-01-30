package com.app.instashare.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.instashare.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileView() {
    val selectedTab = remember { mutableIntStateOf(value = 0) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Column {
                TopAppBar(
                    title = { Text("Username", fontWeight = FontWeight.SemiBold) },
                    actions = {
                        Row {
                            Image(
                                painter = painterResource(R.drawable.add_outlined),
                                contentDescription = null,
                                modifier = Modifier.size(25.dp)
                            )
                            Box(modifier = Modifier.width(20.dp))
                            Icon(Icons.Default.Dehaze, contentDescription = null)
                            Box(modifier = Modifier.width(20.dp))
                        }
                    }
                )
                ProfileSection()
                Box(modifier = Modifier.height(10.dp))
                TabRow(
                    selectedTabIndex = selectedTab.intValue,
                    indicator = { tabPositions ->
                        if (selectedTab.intValue < tabPositions.size) {
                            TabRowDefaults.SecondaryIndicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab.intValue]),
                                color = Color.Black,
                                height = 2.dp
                            )
                        }
                    }
                    ) {
                    listOf(
                        R.drawable.square,
                        R.drawable.reel_outlined,
                        R.drawable.tag,
                    ).forEachIndexed { index, item ->
                        Tab(
                            selected = selectedTab.intValue==index,
                            onClick = {
                                selectedTab.intValue=index
                            },
                            interactionSource = remember { MutableInteractionSource() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
//                                contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(item),
                                contentDescription = null,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    }


                }
                Box(modifier = Modifier.height(2.dp))
            }
        }
        item {
            PostsGrid(Modifier.fillParentMaxSize())
        }

    }
}

@Composable
fun ProfileSection() {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Row {
            AsyncImage(
                model = "https://letsenhance.io/static/8f5e523ee6b2479e26ecc91b9c25261e/1015f/MainAfter.jpg",
                contentDescription = "stories",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )
            Box(modifier = Modifier.width(10.dp))
            Column {
                Text("Username", fontWeight = FontWeight.SemiBold)
                Box(modifier = Modifier.height(5.dp))
                Row {
                    Column {
                        Text("160", fontWeight = FontWeight.SemiBold)
                        Text("Posts")
                    }
                    Box(modifier = Modifier.width(30.dp))
                    Column {
                        Text("160", fontWeight = FontWeight.SemiBold)
                        Text("Followers")
                    }
                    Box(modifier = Modifier.width(30.dp))
                    Column {
                        Text("160", fontWeight = FontWeight.SemiBold)
                        Text("Following")
                    }
                }
            }
        }
        Box(modifier = Modifier.height(10.dp))
        Text("Success is not final, failure is not fatal: It is the courage to continue that counts.")
    }
}

@Composable
fun PostsGrid(modifier: Modifier) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        contentPadding = PaddingValues(0.dp),
        columns = StaggeredGridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalItemSpacing = 2.dp
    ) {
        items(30) {
            AsyncImage(
                model = "https://i0.wp.com/picjumbo.com/wp-content/uploads/beautiful-fall-waterfall-free-image.jpeg?w=600&quality=80",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(color = Color.Red)
            )
        }
    }
}