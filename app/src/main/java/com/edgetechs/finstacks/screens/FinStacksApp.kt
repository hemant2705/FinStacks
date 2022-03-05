package com.edgetechs.finstacks.screens

import android.app.Application
import com.edgetechs.finstacks.common.firebase.FirebaseAuthManager
import com.edgetechs.finstacks.data.firebase.FirebaseFeedPostsRepository
import com.edgetechs.finstacks.data.firebase.FirebaseNotificationsRepository
import com.edgetechs.finstacks.data.firebase.FirebaseSearchRepository
import com.edgetechs.finstacks.data.firebase.FirebaseUsersRepository
import com.edgetechs.finstacks.screens.notifications.NotificationsCreator
import com.edgetechs.finstacks.screens.search.SearchPostsCreator

class FinStacksApp : Application() {
    val usersRepo by lazy { FirebaseUsersRepository() }
    val feedPostsRepo by lazy { FirebaseFeedPostsRepository() }
    val notificationsRepo by lazy { FirebaseNotificationsRepository() }
    val authManager by lazy { FirebaseAuthManager() }
    val searchRepo by lazy { FirebaseSearchRepository() }

    override fun onCreate() {
        super.onCreate()
        NotificationsCreator(notificationsRepo, usersRepo, feedPostsRepo)
        SearchPostsCreator(searchRepo)
    }
}