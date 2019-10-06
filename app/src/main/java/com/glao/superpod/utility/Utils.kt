package com.glao.superpod.utility

import android.app.ProgressDialog
import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.glao.superpod.retrofit.handler.ApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.net.NetworkInterface
import java.text.SimpleDateFormat
import java.util.*

class Utils(private val tag: String) {

    companion object {
        private const val BASE_URL = "https://www.www.www/"

        const val PLAY_STORE_LINK = "https://play.google.com/store/apps/details?id=com.glao.superpod"

        /**
         * Provide Util Class
         * @param context [Context]
         * @return [Utils] Class with TAG
         *
         * @author Pratap Raj Srivastava65
         */
        fun provideUtil(context: Context): Utils {
            var activityName = Utils.getClassName(context.javaClass.name)

            activityName = if (activityName.length > 20) {
                activityName.substring(0, 20)
            } else {
                activityName
            }

            return Utils(activityName)
        }


        /**
         * Used for showing [Toast] to the user
         * @param context [Context]
         * @param message Message to show
         * @param length Time to show [Toast.LENGTH_SHORT] or [Toast.LENGTH_LONG]
         *
         * @author Pratap Raj Srivastava
         */
        fun showToast(context: Context, message: String, length: Int) {
            Toast.makeText(context, message, length).show()
        }

        fun isQRAvailable(prefix: String): Boolean {
            val storageDir = File(Environment.getExternalStorageDirectory().toString(), "WisOpt/QRCodes/")
            storageDir.mkdirs()
            val file = File(storageDir, "$prefix.png")
            return file.exists()
        }

        fun isImageAvailable(fileName: String, groupId: String): Boolean {
            val storageDir = File(Environment.getExternalStorageDirectory().toString(), "WisOpt/Media/$groupId/Images/")
            storageDir.mkdirs()
            val file = File(storageDir, fileName)
            return file.exists()
        }

        fun isFileAvailable(fileName: String, groupId: String): Boolean {
            val storageDir = File(Environment.getExternalStorageDirectory().toString(), "WisOpt/Media/$groupId/Docs/")
            storageDir.mkdirs()
            val file = File(storageDir, fileName)
            return file.exists()
        }

        fun getImage(fileName: String, groupId: String): File {
            val storageDir = File(Environment.getExternalStorageDirectory().toString(), "WisOpt/Media/$groupId/Images/")
            storageDir.mkdirs()
            return File(storageDir, fileName)
        }

        fun getFile(fileName: String, groupId: String): File {
            val storageDir = File(Environment.getExternalStorageDirectory().toString(), "WisOpt/Media/$groupId/Docs/")
            storageDir.mkdirs()
            return File(storageDir, fileName)
        }

        fun getQRFile(prefix: String): File? {
            val storageDir = File(Environment.getExternalStorageDirectory().toString(), "WisOpt/QRCodes/")
            storageDir.mkdirs() // make sure you call mkdirs() and not mkdir()
            val file = File(storageDir, "$prefix.png")
            if (!file.exists()) return null
            return file
        }


        val interfaceService: ApiInterface
            get() {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                return retrofit.create<ApiInterface>(ApiInterface::class.java)
            }

        val pythonService: ApiInterface
            get() {
                val BASE_URL = "https://python.wisopt.com"
                val clientBuilder = OkHttpClient.Builder()
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                clientBuilder.addInterceptor(loggingInterceptor)
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(clientBuilder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                return retrofit.create<ApiInterface>(ApiInterface::class.java)
            }

        fun getInterfaceService(base_url: String): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create<ApiInterface>(ApiInterface::class.java)
        }

        fun getProgressDialog(context: Context): ProgressDialog {
            val progress = ProgressDialog(context)
            progress.setCancelable(false)
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            return progress
        }

        fun getClassName(fullName: String) = fullName.substring(fullName.lastIndexOf('.') + 1, fullName.length)

        const val IMAGE_NOTIFICATION = "image_notification"

        private fun convertToDateObject2(dateString: String): Date {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            return dateFormat.parse(dateString)
        }

        fun calculateDuration(time: String): Int {
            val givenTime = convertToDateObject2(time)
            val currentTime = Date()
            val diff = givenTime.time - currentTime.time
            return if (givenTime.after(currentTime)) {
                (diff / (24 * 60 * 60 * 1000)).toInt()
            } else {
                -1
            }
        }
    }

    private fun isVPNConnected(): Boolean {
        val networkList = ArrayList<String>()
        try {
            for (networkInterface in Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp) {
                    networkList.add(networkInterface.name)
                }
            }
        } catch (ex: Exception) {
            Log.d("isVPNConnected", "Error")
        }
        return networkList.contains("tun0") || networkList.contains("ppp0")
    }

    fun convertToDateObject(dateString: String): Date {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
        return dateFormat.parse(dateString)
    }

    fun convertToDateObject2(dateString: String): Date {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return dateFormat.parse(dateString)
    }
}