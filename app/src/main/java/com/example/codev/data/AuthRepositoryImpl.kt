package com.example.codev.data

import com.example.codev.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())
        val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        emit(Resource.Success(authResult))
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }

    override fun registerUser(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())
        val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        emit(Resource.Success(authResult))
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }

    override fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())
        val authResult = firebaseAuth.signInWithCredential(credential).await()
        emit(Resource.Success(authResult))
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }
}