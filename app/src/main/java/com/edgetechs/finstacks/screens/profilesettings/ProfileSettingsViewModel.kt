package com.edgetechs.finstacks.screens.profilesettings

import com.edgetechs.finstacks.common.AuthManager
import com.edgetechs.finstacks.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener

class ProfileSettingsViewModel(private val authManager: AuthManager,
                               onFailureListener: OnFailureListener) :
        BaseViewModel(onFailureListener),
        AuthManager by authManager