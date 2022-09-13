package kr.pe.paran.library_app.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kr.pe.paran.library_app.utils.Logcat

@ExperimentalPermissionsApi
@Composable
fun GeniePermission(
    permission: String,
    message: String,
    onCanceled: () -> Unit,
    onConfirm: () -> Unit = {},
) {


    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val permissionState = rememberPermissionState(permission = permission)
    var showPermissionDialog by remember { mutableStateOf(false) }

    DisposableEffect(key1 = lifecycleOwner) {

        val eventObserver = LifecycleEventObserver { _, event ->
            Logcat.i(event.toString())

            if (event == Lifecycle.Event.ON_RESUME) {
                when {
                    !permissionState.status.isGranted && permissionState.status.shouldShowRationale -> {
                        showPermissionDialog = true
                    }
                    !permissionState.status.isGranted -> {
                        permissionState.launchPermissionRequest()
                    }
                    permissionState.status.isGranted -> {
                        onConfirm()
                    }
                }
            } else if (event == Lifecycle.Event.ON_PAUSE) {
                showPermissionDialog = false
            }
        }
        lifecycleOwner.lifecycle.addObserver(eventObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(eventObserver)
        }
    }

    if (showPermissionDialog) {
        PermissionDialog(
            message = message,
            onDismiss = {
                showPermissionDialog = false
                onCanceled()
            },
            onConfirm = {
                showPermissionDialog = false
                showApplicationSettings(context = context)
            }
        )
    }
}

@Composable
private fun PermissionDialog(
    message: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text("권한설정")
        },
        text = {
            Text(message)
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(text = "설정")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "취소")
            }
        }
    )
}

private fun showApplicationSettings(context: Context) {
    val appDetail = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.parse("package:" + context.packageName)
    )
    appDetail.addCategory(Intent.CATEGORY_DEFAULT)
    appDetail.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(appDetail)
}
