package com.example.wk.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.wk.FormBeatsApplication
import com.example.wk.data.Form
import com.example.wk.data.FormDao
import com.example.wk.presentation.FirebaseQueryLiveData
import com.google.firebase.firestore.FirebaseFirestore


class FormListViewModel(private val formDao: FormDao): ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val suaColecaoRef = db.collection("form")

    // LiveData para armazenar os resultados da consulta ao Firestore
    val formListLiveData: LiveData<List<Form>> = FirebaseQueryLiveData(suaColecaoRef)

    companion object {
        fun create(application: Application): FormListViewModel {
            val dataBaseInstance = (application as FormBeatsApplication).getAppDatabase()
            val dao = dataBaseInstance.formDao()
            return FormListViewModel(dao)
        }
    }
}