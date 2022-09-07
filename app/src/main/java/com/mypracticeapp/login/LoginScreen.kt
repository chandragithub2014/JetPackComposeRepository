package com.mypracticeapp.login


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mypracticeapp.R
import com.mypracticeapp.ui.theme.DarkBlue

@Preview(showSystemUi = true)
@Composable
fun Login(modifier: Modifier = Modifier,context: Context?=null, loginViewModel: LoginViewModel = viewModel()) {
    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
    loginUserInterface(modifier = modifier,context,loginViewModel)
}

}


@Composable
fun loginUserInterface(modifier: Modifier,context: Context?,loginViewModel: LoginViewModel){


    val emailState = remember {
        mutableStateOf("")
    }
    val passwordState = remember {
        mutableStateOf("")

    }
    val showPassword = remember {
        mutableStateOf(false)
    }

    Text(text = stringResource(id = R.string.welcome_message),
        style = TextStyle(color = Color.Blue, fontSize = 24.sp)
    )
    spacer()
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = emailState.value,
        onValueChange = { emailState.value = it },
        label = { Text(text = "Email") },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)

    )

    spacer()


    TextField(value = passwordState.value,
        onValueChange = { passwordState.value = it },
        modifier = modifier.fillMaxWidth(),
        label = { Text(text = "Password") },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        visualTransformation =
        if (showPassword.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            if (showPassword.value) {
                IconButton(onClick = { showPassword.value = false }) {
                    Icon(imageVector = Icons.Filled.Visibility, contentDescription = null)
                }
            } else {
                IconButton(onClick = { showPassword.value = true }) {
                    Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = null)
                }
            }
        }

    )
    spacer()
    Button(
        onClick = {
            loginViewModel.validateUserCredential(emailState.value, passwordState.value)
        },
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = DarkBlue,
            contentColor = Color.White
        ),

        ) {

        Text(
            text = "Login"
        )

    }
    spacer()
    Text(
        text = loginViewModel._validationResponse.value ?: "",
        style = TextStyle(color = Color.Black, fontSize = 24.sp)
    )
}



@Composable
fun spacer(){
    Spacer(modifier = Modifier.height(10.dp))
}

fun validate(email:String,password:String,context: Context?){
    if(email.isBlank() && password.isBlank()){
        Toast.makeText(context,"Email and Password are blank",Toast.LENGTH_LONG).show()
    }else if(email.isBlank() && password.isNotBlank()){
        Toast.makeText(context,"Email is blank",Toast.LENGTH_LONG).show()
    }
    else if(email.isNotBlank() && password.isBlank()){
        Toast.makeText(context,"Password is blank",Toast.LENGTH_LONG).show()
    }
    else{
        Toast.makeText(context,"Login successful",Toast.LENGTH_LONG).show()
    }
}



