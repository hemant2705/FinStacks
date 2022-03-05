package com.edgetechs.finstacks.common.firebase

import com.edgetechs.finstacks.data.firebase.common.auth
import com.edgetechs.finstacks.common.AuthManager
import com.edgetechs.finstacks.common.toUnit
import com.google.android.gms.tasks.Task

class FirebaseAuthManager : AuthManager {
    override fun signOut() {
        auth.signOut()
    }

    override fun signIn(email: String, password: String): Task<Unit> =
        auth.signInWithEmailAndPassword(email, password).toUnit()
}