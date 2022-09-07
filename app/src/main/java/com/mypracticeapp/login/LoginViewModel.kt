package com.mypracticeapp.login

import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

const val  emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
class LoginViewModel : ViewModel() {

    val _validationResponse : MutableState<String> = mutableStateOf("")

    fun validateUserCredential(email : String, password : String){
        if(email.isBlank() && password.isBlank()){
            _validationResponse.value = "Email and Password are blank"
        }else if(email.isBlank() && password.isNotBlank()){
            _validationResponse.value = "Email is blank"
        }
        else if(email.isNotBlank() && password.isBlank()){
            _validationResponse.value = "Password is blank"
        }
        else if(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length>4){
            _validationResponse.value = "Login succesful"
        }else {
            _validationResponse.value = "Invalid credentials"
        }
    }


}