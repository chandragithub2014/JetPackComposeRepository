package com.mypracticeapp.login

import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {

    private val _validationResponse: MutableState<String> = mutableStateOf("")
       val validationResponse:State<String> = _validationResponse


    fun validateUserCredential(email: String, password: String) {
        viewModelScope.launch {
            if (email.isBlank() && password.isBlank()) {
                _validationResponse.value = "Email and Password are blank"


            } else if (email.isBlank() && password.isNotBlank()) {
                _validationResponse.value = "Email is blank"


            } else if (email.isNotBlank() && password.isBlank()) {
                _validationResponse.value = "Password is blank"


            } else if (android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                    .matches() && password.length > 4
            ) {
                _validationResponse.value = "Login succesful"


            } else {
                _validationResponse.value = "Invalid credentials"


            }
        }
    }

    fun reset(){
        _validationResponse.value = ""
    }


}