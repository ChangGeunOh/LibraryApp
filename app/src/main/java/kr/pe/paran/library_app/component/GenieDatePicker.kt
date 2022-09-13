package kr.pe.paran.library_app.component

import android.app.DatePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
fun GenieDatePicker(onDismiss: (String?) -> Unit) {

    val context = LocalContext.current
    val now = Calendar.getInstance()

    val picker = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val date = String.format("%4d.%02d.%02d", year, month, dayOfMonth)
            onDismiss(date)
        },
        now.get(Calendar.YEAR),
        now.get(Calendar.MONTH),
        now.get(Calendar.DAY_OF_MONTH)
    )
    picker.setOnCancelListener { onDismiss(null) }
    picker.show()
}