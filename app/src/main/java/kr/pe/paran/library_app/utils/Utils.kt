package kr.pe.paran.library_app.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.Settings
import androidx.core.content.FileProvider
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.io.File
import java.io.FileOutputStream

object Utils {

    fun getBarCode(code: String): Bitmap? {
        val multiFormatWriter = MultiFormatWriter()
        return try {
            val bitMatrix: BitMatrix =
                multiFormatWriter.encode(code, BarcodeFormat.PDF_417, 1000, 300)
            val barcodeEncoder = BarcodeEncoder()
            barcodeEncoder.createBitmap(bitMatrix)
        } catch (e: WriterException) {
            e.printStackTrace()
            null
        }
    }

    fun showApplicationSettings(context: Context) {
        val appDetail = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:" + context.packageName)
        )
        appDetail.addCategory(Intent.CATEGORY_DEFAULT)
        appDetail.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(appDetail)
    }

    fun shareBarCode(context: Context, barCode: String, title: String) {
        saveBarCode(context = context, barCode = barCode, title = title)?.let {
            shareFile(context = context, title = title, uri = it)
        }
    }

    private fun saveBarCode(context: Context, barCode: String, title: String): Uri? {
        var imageUri: Uri? = null
        getBarCode(code = barCode)?.let { bitmap ->
            val imageFile = File(context.cacheDir, "$title$barCode.png")
            imageFile.outputStream().use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                it.flush()
            }
            imageUri = FileProvider.getUriForFile(context, "${context.packageName}.provider", imageFile)
        }
        return imageUri
    }

    private fun shareFile(context: Context, title: String, uri: Uri) {

        val dataType = "application/image"
        val intent = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TITLE, "$title BarCode Image...")
            type = dataType
            putExtra(Intent.EXTRA_STREAM, uri)
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }, null)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        context.startActivity(intent)
    }
}

