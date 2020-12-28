package news.feed.timesofindianewsfeed

import android.os.AsyncTask
import android.util.Log
import java.lang.Exception
import java.net.URL

enum class DownloadStatus{
    OK, IDLE, NOT_INITIALISED, FAILED_OR_EMPTY, PERMISSIONS_ERROR, ERROR
}
class GetRawData : AsyncTask<String, Void, String>() {
    private  val TAG = "GetRawData"
    private var downloadStatus = DownloadStatus.IDLE

    override fun doInBackground(vararg params: String?): String {
        if(params[0]==null){
        downloadStatus = DownloadStatus.NOT_INITIALISED
        return "No URL Specified"
        }
        try {
            downloadStatus = DownloadStatus.OK
            return URL(params[0]).readText()

        } catch (e: Exception) {
            val errorMessage = "Error message: ${e.message} "

            Log.e(TAG, errorMessage)
            return errorMessage
        }

    }

    override fun onPostExecute(result: String?) {
        Log.d(TAG, "onPostExecute is called $result")
    }


}