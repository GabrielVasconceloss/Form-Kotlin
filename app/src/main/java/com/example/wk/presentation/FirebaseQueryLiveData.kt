package com.example.wk.presentation
import androidx.lifecycle.LiveData
import com.example.wk.data.Form
import com.google.firebase.firestore.*

class FirebaseQueryLiveData(private val query: Query) : LiveData<List<Form>>() {

    private var registration: ListenerRegistration? = null

    private val listener = MyValueEventListener()

    override fun onActive() {
        super.onActive()
        registration = query.addSnapshotListener(listener)
    }

    override fun onInactive() {
        super.onInactive()
        registration?.remove()
    }

    private inner class MyValueEventListener : EventListener<QuerySnapshot> {
        override fun onEvent(snapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) {
            if (exception != null) {
                // Handle error
                return
            }

            // Processar documentos
            val formList = snapshot?.documents?.mapNotNull { documentSnapshot ->
                documentSnapshot.toObject(Form::class.java)
            } ?: emptyList()

            value = formList
        }
    }
}

