package com.debend.firebase_authentication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.debend.firebase_authentication.ui.theme.FireBaseAuthenticationTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    val auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FireBaseAuthenticationTheme {
                AppNavigation()
            }
        }
    }

    fun saveUserToFirestore(uid: String, email: String) {
        val db = FirebaseFirestore.getInstance()
        val user = hashMapOf("uid" to uid, "email" to email)
        db.collection("users").document(uid).set(user)
    }

    fun signUp(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(true, auth.currentUser?.uid)
            } else {
                onResult(false, task.exception?.message)
            }
        }
    }

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(true, auth.currentUser?.uid)
            } else {
                onResult(false, task.exception?.message)
            }
        }
    }


    @Composable
    fun SignUpScreen(navController: NavController) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val context = LocalContext.current
        Column {
            TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            Button(onClick = {
                signUp(email, password) { success, uid ->
                    if (success) navController.navigate("home/$uid")
                    else Toast.makeText(context, "Sign-Up Failed", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Sign Up")
            }
        }
    }

    @Composable
    fun LoginScreen(navController: NavController) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val context = LocalContext.current
        Column {
            TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            Button(onClick = {
                login(email, password) { success, uid ->
                    if (success) navController.navigate("home/$uid")
                    else Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Login")
            }
        }
    }

    @Composable
    fun HomeScreen(uid: String) {
        var email by remember { mutableStateOf("") }
        LaunchedEffect(uid) {
            getUserDetails(uid) { email = it ?: "Unknown" }
        }
        Column {
            Text("User ID: $uid")
            Text("Email: $email")
        }
    }

    private fun getUserDetails(uid: String, onResult: (String?) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(uid).get().addOnSuccessListener { document ->
            onResult(document.getString("email"))
        }.addOnFailureListener {
            onResult(null)
        }
    }

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "login") {
            composable("login") { LoginScreen(navController) }
            composable("signup") { SignUpScreen(navController) }
            composable("home/{uid}") { backStackEntry ->
                val uid = backStackEntry.arguments?.getString("uid") ?: ""
                HomeScreen(uid)
            }
        }
    }
}