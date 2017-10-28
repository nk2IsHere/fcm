package ga.nk2ishere.dev.fcm

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object App {

    val SERVER_FCM_TOKEN = "AAAA06eaxd8:APA91bFq7cXtTmDnnGMjKScgRSS9AQRHZw71IXVwiTWVkYePeVixG90K4F30Da-V-EamqC7rXw7ltYJ1f_o8fz_WW0RxW9dHl0PSLffsD0byALA0rcBdApLOP7EItLmbQFx5nK3rPH4d"
    val PHONE_FCM_TOKENS = listOf("dPHopLU30A0:APA91bFKh-PdE487CbMg8dtSAj3A_1qpI_dMMo4vCXgKnje1vX4f9oxrDftBkD-S4VbYHgzYLVt9PGLEIUMlqSWQnX33pZy2PaIsHX2cge4FVPjSsQMOfVGoFZsmgKETG3VecUuhvybo")

    val sender = Sender(SERVER_FCM_TOKEN)

    @JvmStatic
    fun main(args: Array<String>) {
        sender.send(Message.MessageBuilder()
                .addRegistrationToken(PHONE_FCM_TOKENS) //phone tokens
                .restrictedPackageName("com.nk2rougsig.tcq") //package name
                .addData("id", 2) //data to handle in handler service (in app)
                .addData("msg", "TEST")
                .build(), object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                System.err.println("ERROR")
            }

            override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                System.out.println(response)
            }
        })
    }
}