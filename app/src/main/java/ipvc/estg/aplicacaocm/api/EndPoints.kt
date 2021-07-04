package ipvc.estg.aplicacaocm.api

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.*

interface EndPoints {

    //utilizador
    @GET("api/utilizador")
    fun getUtilizadores(): Call<List<Utilizador>>

    @GET("api/utilizador/{id}")
    fun getUtilizadorById(@Path("id") id:Int):Call<Utilizador>

    @FormUrlEncoded
    @POST("api/login")
    fun login(@Field("nome") nome: String?, @Field("password") password: String?): Call<OutputLogin>
}