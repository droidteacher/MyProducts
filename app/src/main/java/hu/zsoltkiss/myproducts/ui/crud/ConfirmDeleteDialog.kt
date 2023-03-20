package hu.zsoltkiss.myproducts.ui.crud

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable

fun ConfirmDeleteDialog(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(text = "Are you sure?")
        },
        text = {
            Text("This product will be removed from database.")
        },
        confirmButton = {
            Button(
                onClick = { onConfirm() }
            ) {
                Text("Remove")
            }
        },
        dismissButton = {
            Button(
                onClick = { onCancel() }
            ) {
                Text("Cancel")
            }
        }
    )
}

