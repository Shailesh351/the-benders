package `in`.webdevlabs.campuscommerce.utils

import `in`.webdevlabs.campuscommerce.App
import `in`.webdevlabs.campuscommerce.model.User
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseUtil {

    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var firebaseUser: FirebaseUser? = firebaseAuth.currentUser
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var deviceID = ""

    fun addCurrentUserToFirebaseDatabase() {
        val databaseRef: DatabaseReference = database.getReference("users")
        val user = User(firebaseAuth.currentUser?.displayName, firebaseAuth.currentUser?.email)
        databaseRef.child(firebaseAuth.currentUser?.uid).setValue(user)
    }
}