package av.project.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private val answer = String()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {

            val ans = getCuontContacts()
            println(ans)
            val text = ans
            val duration = Toast.LENGTH_LONG
            val toast = Toast.makeText(this.context, text, duration)
            toast.show()
        }
    }
// android.content.Context
    fun getCuontContacts(): String? {
        println("get count start")
        val url = "http://46.17.106.65/api/get-count-contacts"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onResponse(call: Call, response: Response) {
//                val body = response.body()?.string()
////                println(body)
//                if (response.code() == 200) {
//                    println(body)
//
//                    val textView = R.id.textView as TextView
//                    textView.text = body
//
//
//                } else {
//                    println("Fail login")
//                }
//
//            }
//
//            override fun onFailure(call: Call, e: IOException) {
//                println("Fail login")
////                println(e)
//            }
//        })
        val call = client.newCall(request)
        val response = call.execute()

        return response.body()?.string()
    }


    fun toastMe(view: View, str: String) {
        // val myToast = Toast.makeText(this, message, duration);
        val myToast = Toast.makeText(context, "Hello Toast!", Toast.LENGTH_SHORT)
        myToast.show()
    }

}