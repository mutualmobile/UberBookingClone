package com.example.uberbookingexperience.ui.screens.schedulePickup.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogScope
import com.vanpra.composematerialdialogs.MaterialDialogState

@Composable
fun UberDateTimePicker(
    dialogState: MaterialDialogState,
    content: @Composable MaterialDialogScope.() -> Unit
) {
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        onCloseRequest = { it.hide() },
        backgroundColor = MaterialTheme.colorScheme.surface,
        content = content
    )
}
