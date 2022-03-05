package com.edgetechs.finstacks.screens.addfriends

import androidx.lifecycle.LiveData
import com.edgetechs.finstacks.common.map
import com.edgetechs.finstacks.data.FeedPostsRepository
import com.edgetechs.finstacks.data.UsersRepository

import com.edgetechs.finstacks.models.User
import com.edgetechs.finstacks.screens.common.BaseViewModel

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks

class AddFriendsViewModel(onFailureListener: OnFailureListener,
                          private val usersRepo: UsersRepository,
                          private val feedPostsRepo: FeedPostsRepository)
    : BaseViewModel(onFailureListener) {
    val userAndFriends: LiveData<Pair<User, List<User>>> =
            usersRepo.getUsers().map { allUsers ->
                val (userList, otherUsersList) = allUsers.partition {
                    it.uid == usersRepo.currentUid()
                }
                userList.first() to otherUsersList
            }

    fun setFollow(currentUid: String, uid: String, follow: Boolean): Task<Void> {
        return (if (follow) {
            Tasks.whenAll(
                    usersRepo.addFollow(currentUid, uid),
                    usersRepo.addFollower(currentUid, uid),
                    feedPostsRepo.copyFeedPosts(postsAuthorUid = uid, uid = currentUid))
        } else {
            Tasks.whenAll(
                    usersRepo.deleteFollow(currentUid, uid),
                    usersRepo.deleteFollower(currentUid, uid),
                    feedPostsRepo.deleteFeedPosts(postsAuthorUid = uid, uid = currentUid))
        }).addOnFailureListener(onFailureListener)
    }
}