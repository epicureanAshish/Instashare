package com.app.instashare.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.app.instashare.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val showBottomSheet = remember { mutableStateOf(false) }
    Box {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            HeaderView()
            StoriesView()
            SuggestedPostsFeed(showBottomSheet)
        }
        if(showBottomSheet.value)
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet.value= false
                },
                sheetState = sheetState
            ) {
                Button(onClick = {
                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        showBottomSheet.value=false
                    }
                }) {
                    Text(text = "clicked")
                }
            }
    }
}

@Composable
fun HeaderView() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.instagram_text),
            contentDescription = "logo_text",
            modifier = Modifier.width(120.dp)
        )
        Row {
            Image(
                painter = painterResource(R.drawable.favorite),
                contentDescription = "message",
                modifier = Modifier.width(35.dp)
            )
            Box(modifier = Modifier.width(20.dp))
            Image(
                painter = painterResource(R.drawable.message_outlined),
                contentDescription = "message",
                modifier = Modifier.width(30.dp)
            )
        }
    }

}

@Composable
fun StoriesView(){
    LazyRow(
        contentPadding = PaddingValues(10.dp)
    ) {
       itemsIndexed(
            listOf(1,2)
        ){index, item->
           Row {
               Box(
                   contentAlignment = Alignment.BottomEnd
               ) {
                   AsyncImage(
                       model = "https://letsenhance.io/static/8f5e523ee6b2479e26ecc91b9c25261e/1015f/MainAfter.jpg",
                       contentDescription = "stories",
                       contentScale = ContentScale.Crop,
                       modifier = Modifier
                           .size(70.dp)
                           .clip(CircleShape)
                   )
                   if(item==1) {
                       Box(
                           modifier = Modifier
                               .clip(CircleShape)
                               .background(color = Color.White),
                       ) {
                           Icon(
                               imageVector = Icons.Default.AddCircle,
                               contentDescription = null, tint = Color(0xFF03A9F4)
                           )
                       }
                   }

               }
               Box(modifier = Modifier.width(12.dp))
           }
        }

    }
}

@Composable
fun SuggestedPostsFeed(showBottomSheet: MutableState<Boolean>) {
    Column(

    ) {
        for(i in 1..10) {
            PostView(showBottomSheet)
        }

    }
}

@Composable
fun PostView(showBottomSheet: MutableState<Boolean>) {
    val liked = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ){
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
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Username", fontWeight = FontWeight.SemiBold)
                    Text(text = "Location", fontSize = 10.sp, lineHeight = 10.sp)
                }
            }
            Box {
                IconButton(
                    onClick = {
                        showBottomSheet.value=true
//                        menuOpened.value=true
                    }
                ) {
                    Icon(Icons.Default.MoreHoriz, contentDescription = "more")
                }

            }
        }

        Box(modifier = Modifier.height(5.dp))
        //Post media
        AsyncImage(
            model= "https://letsenhance.io/static/8f5e523ee6b2479e26ecc91b9c25261e/1015f/MainAfter.jpg",
            contentDescription = "post",
            modifier = Modifier.fillMaxWidth()
        )
        Box(modifier = Modifier.height(10.dp))
        //Likes, comments, shares
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Image(
                painter = painterResource(if(liked.value) R.drawable.favorite_filled else R.drawable.favorite),
                contentDescription = "Likes",
                colorFilter = ColorFilter.tint(color = if(liked.value) Color.Red else Color.Black),
                modifier = Modifier
                    .width(25.dp)
                    .clickable {
                        liked.value = !liked.value
                    }
            )
            Box(modifier = Modifier.width(5.dp))
            Text(text = "40K", color = Color.Black, fontSize = 16.sp, lineHeight = 16.sp, fontWeight = FontWeight.SemiBold)
            Box(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(R.drawable.comment),
                contentDescription = "Comments",
                modifier = Modifier.width(25.dp)
            )
            Box(modifier = Modifier.width(5.dp))
            Text(text = "649", color = Color.Black, fontSize = 16.sp, lineHeight = 16.sp, fontWeight = FontWeight.SemiBold)
            Box(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(R.drawable.send),
                contentDescription = "Shares",
                modifier = Modifier.width(25.dp)
            )
            Box(modifier = Modifier.width(5.dp))
            Text(text = "210K", color = Color.Black, fontSize = 16.sp, lineHeight = 16.sp, fontWeight = FontWeight.SemiBold)
        }
        Box(modifier = Modifier.height(5.dp))
        //Caption with user name
        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(
                fontWeight = FontWeight.SemiBold
            )){
                append(text = "Username ")
            }
            append(text = "Chameleons or chamaeleons are a distinctive and highly specialized clade of Old World lizards with 200 species described as of June 2015.")
        },
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 10.dp)

            )
        Box(modifier = Modifier.height(10.dp))
        Text(text = "4 December 2024", color = Color.Gray, fontSize = 10.sp, lineHeight = 10.sp, modifier = Modifier.padding(horizontal = 10.dp))
        Box(modifier = Modifier.height(20.dp))
    }
}

