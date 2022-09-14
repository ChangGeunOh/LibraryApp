package kr.pe.paran.library_app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.app.NotificationCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.pe.paran.library_app.repository.Repository
import kr.pe.paran.library_app.repository.local.LocalCacheDataStore
import kr.pe.paran.library_app.repository.local.LocalTemporaryDataStore
import kr.pe.paran.library_app.screen.member.MemberViewModel
import kr.pe.paran.library_app.utils.Logcat
import javax.inject.Inject

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@ExperimentalPagerApi
class MyFirebaseMsgService: FirebaseMessagingService() {


    override fun onNewToken(token: String) {
        super.onNewToken(token)
            LocalTemporaryDataStore.token = token
            Logcat.i("Token>$token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.i(":::::", "RemoteMessage        collapseKey>${message.collapseKey}")
        Log.i(":::::", "RemoteMessage               data>${message.data.toString()}")
        Log.i(":::::", "RemoteMessage            rawData>${message.rawData.toString()}")
        Log.i(":::::", "RemoteMessage               from>${message.from}")
        Log.i(":::::", "RemoteMessage          messageId>${message.messageId}")
        Log.i(":::::", "RemoteMessage        messageType>${message.messageType}")
        Log.i(":::::", "RemoteMessage       notification>${message.notification.toString()}")
        Log.i(":::::", "RemoteMessage notification body>${message.notification?.body}")
        Log.i(":::::", "RemoteMessage notification title>${message.notification?.title}")
        Log.i(":::::", "RemoteMessage notification Image>${message.notification?.imageUrl}")
        Log.i(":::::", "RemoteMessage           priority>${message.priority}")
        Log.i(":::::", "RemoteMessage           senderId>${message.senderId}")
        Log.i(":::::", "RemoteMessage           sentTime>${message.sentTime}")
        Log.i(":::::", "RemoteMessage                 to>${message.to}")
        Log.i(":::::", "RemoteMessage                ttl>${message.ttl}")

//        message.notification?.let {
//            Log.d(":::::", "Message Notification Body: ${it.body}")
//            it.body?.let { body -> sendNotification(body) }
//        }
    }

    private fun sendNotification(messageBody: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0 /* Request code */, intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = "fcm_default_channel"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("FCM Message")
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }


}
