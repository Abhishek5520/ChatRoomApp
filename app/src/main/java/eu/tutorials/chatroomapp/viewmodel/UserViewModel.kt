package eu.tutorials.chatroomapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import eu.tutorials.chatroomapp.Injection
import eu.tutorials.chatroomapp.data.Result
import eu.tutorials.chatroomapp.data.User
import eu.tutorials.chatroomapp.data.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val userRepository: UserRepository

    init {
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
    }

    private val _userResult = MutableLiveData<Result<User>>()
    val userResult: LiveData<Result<User>> get() = _userResult

    fun getCurrentUser() {
        viewModelScope.launch {
            _userResult.value = userRepository.getCurrentUser()
        }
    }

}