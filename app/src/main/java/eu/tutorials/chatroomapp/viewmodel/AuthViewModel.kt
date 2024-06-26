package eu.tutorials.chatroomapp.viewmodel

import eu.tutorials.chatroomapp.data.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import eu.tutorials.chatroomapp.Injection
import eu.tutorials.chatroomapp.data.User
import eu.tutorials.chatroomapp.data.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val userRepository: UserRepository

    init {
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
    }

    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult

    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.signUp(email, password, firstName, lastName)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.login(email, password)
        }
    }

    private val _userResult = MutableLiveData<Result<User>>()
    val userResult: LiveData<Result<User>> get() = _userResult

    fun getCurrentUser() {
        viewModelScope.launch {
            _userResult.value = userRepository.getCurrentUser()
        }
    }
}
