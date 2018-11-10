package cool.mixi.dica.util

import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.regex.Pattern


private val urlPattern = Pattern.compile(
    "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
            + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
            + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
    Pattern.CASE_INSENSITIVE or Pattern.MULTILINE or Pattern.DOTALL)

fun Any.eLog(log: String) = Log.e(this::class.java.simpleName, "===========> $log")
fun Any.iLog(log: String) = Log.i(this::class.java.simpleName, "===========> $log")
fun Any.dLog(log: String) = Log.d(this::class.java.simpleName, "===========> $log")

fun String.md5(): String {
    try {
        val instance: MessageDigest = MessageDigest.getInstance("MD5")
        val digest:ByteArray = instance.digest(this.toByteArray())
        var sb = StringBuffer()
        for (b in digest) {
            var i :Int = b.toInt() and 0xff
            var hexString = Integer.toHexString(i)
            if (hexString.length < 2) {
                hexString = "0" + hexString
            }
            sb.append(hexString)
        }
        return sb.toString()

    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }

    return ""
}

fun String.urls(): ArrayList<String> {
    var urls = ArrayList<String>()
    val matcher = urlPattern.matcher(this)
    while (matcher.find()) {
        val matchStart = matcher.start(1)
        val matchEnd = matcher.end()
        urls.add(this.substring(matchStart, matchEnd))
    }
    return urls
}
