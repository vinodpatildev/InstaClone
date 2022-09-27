package com.vinodpatildev.instaclone.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vinodpatildev.instaclone.DestinationScreen
import com.vinodpatildev.instaclone.InstaViewModel
import com.vinodpatildev.instaclone.R
import com.vinodpatildev.instaclone.main.CheckSignedIn
import com.vinodpatildev.instaclone.main.CommonProgressSpinner
import com.vinodpatildev.instaclone.main.navigateTo

@Composable
fun LoginScreen(navController: NavController, viewModel: InstaViewModel) {
    CheckSignedIn(navController = navController, viewModel = viewModel)
    val focus = LocalFocusManager.current
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(
                    rememberScrollState()
                ),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val emailState = remember {
                mutableStateOf(TextFieldValue())
            }
            val passwordState = remember {
                mutableStateOf(TextFieldValue())
            }

            Image(
                painter = painterResource(id = R.drawable.ig_logo), contentDescription = null,
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp)
            )
            Text(
                text = "Login",
                modifier = Modifier
                    .padding(8.dp)
            )
            OutlinedTextField(
                value = emailState.value, onValueChange = { emailState.value = it },
                modifier = Modifier.padding(8.dp),
                label = { Text(text = "Email") },
            )
            OutlinedTextField(
                value = passwordState.value, onValueChange = { passwordState.value = it },
                modifier = Modifier.padding(8.dp),
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = {
                    focus.clearFocus(force = true)
                    viewModel.onLogin(emailState.value.text, passwordState.value.text)
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "LOGIN UP")
            }

            Row() {
                Text(
                    text = "Not a user? Go to",
                    modifier = Modifier
                        .padding(end = 0.dp)
                        .padding(8.dp)
                )
                Text(
                    text = "signup",
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(start = 0.dp)
                        .padding(8.dp)
                        .clickable {
                            navigateTo(
                                navController = navController,
                                dest= DestinationScreen.Signup
                            )
                        }
                )
            }
        }
        val isLoading:Boolean = viewModel.inProgress.value ?: false
        if(isLoading){
            CommonProgressSpinner()
        }
    }
}