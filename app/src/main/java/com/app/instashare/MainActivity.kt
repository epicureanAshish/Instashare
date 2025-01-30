package com.app.instashare

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.instashare.ui.screens.DashboardView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.delay


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Navigation()
        }
    }
}
@HiltAndroidApp
class MyApp : Application()

//Navigation
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable(route = "splash") {
            SplashView(navController)
        }
        composable(route = "login") {
            LoginView(navController)
        }
        composable(route = "dashboard") {
            DashboardView(navController)
        }
    }
}


//Splash View
@Composable
fun SplashView(navController: NavHostController) {
    LaunchedEffect(true) {
        delay(3000L)
        navController.navigate("login")
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.instagram_logo),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
    }
}

//Login View
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(navController: NavHostController) {
    val focusManager = LocalFocusManager.current

    val userNameField = remember { mutableStateOf(TextFieldValue("")) }
    val passwordField = remember { mutableStateOf(TextFieldValue("")) }
    val showPassword = remember { mutableStateOf(value = false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = R.drawable.instagram_text),
                contentDescription = null,
                modifier = Modifier.width(170.dp)
            )
            Box(modifier = Modifier.height(10.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(10.dp)
                    ),
                value = userNameField.value,
                onValueChange = {
                    userNameField.value = it
                },
//                interactionSource= interactionSource,
                placeholder = {
                    Text(text = "Phone number, email and username", fontSize = 15.sp)
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                )
            )
            Box(modifier = Modifier.height(10.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(10.dp)
                    ),
                value = passwordField.value,
                onValueChange = {
                    passwordField.value = it
                },
                placeholder = {
                    Text(text = "Password", fontSize = 15.sp)
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    if (showPassword.value)
                        IconButton(onClick = {
                            showPassword.value = false
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "hide_password"
                            )
                        }
                    else
                        IconButton(onClick = {
                            showPassword.value = true
                        }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "show_password"
                            )
                        }

                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
            Box(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF03A9F4),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    focusManager.clearFocus()
                    navController.navigate("dashboard")
                }) {
                Text(text = "Log In", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Box(modifier = Modifier.height(10.dp))
            Text(
                text = buildAnnotatedString {
                    append(text = "Forgot your login details?")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(text = " Get help logging in.")
                    }
                },
                fontSize = 14.sp, fontWeight = FontWeight.Normal, color = Color.Gray
            )
            Box(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(modifier = Modifier.weight(1f))
                Text(
                    text = " Or ",
                    fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray
                )
                Divider(modifier = Modifier.weight(1f))
            }
            Box(modifier = Modifier.height(10.dp))
            Text(
                text = "Log in with facebook",
                fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF03A9F4)
            )
            Box(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = buildAnnotatedString {
                    append(text = "Don\'t have an account?")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(text = " Sign up.")
                    }
                },
                fontSize = 14.sp, fontWeight = FontWeight.Normal, color = Color.Gray
            )
            Box(modifier = Modifier.height(10.dp))


        }
    }
}



