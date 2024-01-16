package com.chintek.example.asrdemo.presentation.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorDialog(
    title: String = "注意",
    contentMsg: String?,
    onDismiss: () -> Unit = {}
) {
    contentMsg?.let {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(text = title) },
            text = { Text(it) },
            confirmButton = {},
            dismissButton = {
                Button(
                    onClick = onDismiss) {
                    Text("OK")
                }
            }
        )
    }
}