package av.project.base



import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import okhttp3.*
import java.io.*
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

//    private val client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            val text = "Try login!"
            val duration = Toast.LENGTH_LONG
            val toast = Toast.makeText(this.context, text, duration)
            toast.show()

//            get data from text editors

            val userName = view.findViewById<EditText>(R.id.editTextTextUserName).text.toString()
            val pass = view.findViewById<EditText>(R.id.editTextTextPassword).text.toString()
            println(userName)
            println(pass)


            login(userName, pass)

//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    fun login(user: String, password: String){
        println("login start")

        val url  = "http://46.17.106.65/api/login"
//        val url  = "http://127.0.0.1:8000/api/login"

        val client: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .callTimeout(10, TimeUnit.SECONDS)
                .build()


//        try POST
        val formBody = FormBody.Builder()
            .add("username", user).add("password", password)
            .build()
        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

//        client.newCall(request).execute().use { response ->
//            println("Response completed: $response")
//        }
        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                if (response.code() == 200) {
//                    println(body)
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                } else {
                    println("Fail login")
                    println(body)
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Fail login")
                println(e)
            }
        })

    }

}


