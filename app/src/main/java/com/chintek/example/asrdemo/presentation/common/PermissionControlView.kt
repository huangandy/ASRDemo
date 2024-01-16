package com.chintek.example.asrdemo.presentation.common

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionControlView(
    grantedContent: @Composable () -> Unit
) {
    val audioPermissionState = rememberPermissionState(
        Manifest.permission.RECORD_AUDIO
    )

    if (audioPermissionState.status.isGranted) {
        grantedContent()
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (!audioPermissionState.status.isGranted) {
                Button(onClick = { audioPermissionState.launchPermissionRequest() }) {
                    Text("Request audio permission")
                }
            }
        }
    }
}