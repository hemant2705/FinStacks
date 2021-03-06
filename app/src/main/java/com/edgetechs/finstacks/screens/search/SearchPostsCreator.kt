package com.edgetechs.finstacks.screens.search

import android.util.Log
import androidx.lifecycle.Observer
import com.edgetechs.finstacks.common.BaseEventListener
import com.edgetechs.finstacks.common.Event
import com.edgetechs.finstacks.common.EventBus
import com.edgetechs.finstacks.data.SearchRepository
import com.edgetechs.finstacks.models.SearchPost

class SearchPostsCreator(searchRepo: SearchRepository) : BaseEventListener() {
    init {
        EventBus.events.observe(this, Observer {
            it?.let { event ->
                when (event) {
                    is Event.CreateFeedPost -> {
                        val searchPost = with(event.post) {
                            SearchPost(
                                    image = image,
                                    caption = caption,
                                    postId = id)
                        }
                        searchRepo.createPost(searchPost).addOnFailureListener {
                            Log.d(TAG, "Failed to create search post for event: $event", it)
                        }
                    }
                    else -> {
                    }
                }
            }
        })
    }

    companion object {
        const val TAG = "SearchPostsCreator"
    }
}