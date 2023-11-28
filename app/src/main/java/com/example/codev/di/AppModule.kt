package com.example.codev.di

import com.example.codev.utils.Constants.NAME_PROPERTY
import com.example.codev.utils.Constants.POSTS_COLLECTION
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideQueryPostsByName() = FirebaseFirestore.getInstance()
        .collection(POSTS_COLLECTION)
        .orderBy(NAME_PROPERTY, Query.Direction.ASCENDING)

}