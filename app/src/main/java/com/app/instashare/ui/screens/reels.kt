package com.app.instashare.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.app.instashare.R

@Composable
fun ReelsView(){
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.Black)
    ){
        Column {
            TopAppBar(
                backgroundColor = Color.Transparent,
                title = { Text("Reels", color = Color.White) },
                actions = {
                    Icon(Icons.Default.CameraAlt, contentDescription =null,
                        tint = Color.White
                        )
                }
           )
            Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            ReelInfo(Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(R.drawable.favorite), contentDescription = null, modifier = Modifier.size(25.dp), colorFilter = ColorFilter.tint(Color.White))
                Box(modifier = Modifier.height(10.dp))
                Text(
                    text = "1,041",
                    fontSize = 16.sp,
                    lineHeight = 16.sp, color = Color.White
                )
                Box(modifier = Modifier.height(20.dp))
                Image(painter = painterResource(R.drawable.comment), contentDescription = null, modifier = Modifier.size(25.dp), colorFilter = ColorFilter.tint(Color.White))
                Box(modifier = Modifier.height(10.dp))
                Text(
                    text = "1,041",
                    fontSize = 16.sp,
                    lineHeight = 16.sp, color = Color.White
                )
                Box(modifier = Modifier.height(20.dp))
                Image(painter = painterResource(R.drawable.send), contentDescription = null, modifier = Modifier.size(25.dp), colorFilter = ColorFilter.tint(Color.White))
                Box(modifier = Modifier.height(10.dp))
                Text(
                    text = "1,041",
                    fontSize = 16.sp,
                    lineHeight = 16.sp, color = Color.White
                )
                Box(modifier = Modifier.height(100.dp))
            }
        }
        }
    }
}

@Composable
fun ReelInfo(modifier: Modifier) {
    Column(modifier = modifier.padding(10.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = "https://letsenhance.io/static/8f5e523ee6b2479e26ecc91b9c25261e/1015f/MainAfter.jpg",
                contentDescription = "profile_photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
            )
            Box(modifier = Modifier.width(10.dp))
            Text(text = "Username", fontWeight = FontWeight.SemiBold, color = Color.White)
        }
        Box(modifier = Modifier.height(10.dp))
        Text(
            text = "Success is not final, failure is not fatal: It is the courage to continue that counts.",
            fontSize = 14.sp,
            lineHeight = 14.sp, color = Color.White
        )

    }
}