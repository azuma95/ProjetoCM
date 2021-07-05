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

    //Ocorrencia
    @GET("api/ocorrencia")
    fun getOcorrencias(): Call<List<Ocorrencia>>

    @GET("api/ocorrencia/{id}")
    fun getOcorrenciaById(@Path("id") id: Int): Call<Ocorrencia>

    //post
    @FormUrlEncoded
    @POST("api/ocorrencia_post")
    fun postOcorrencia(@Field("descricao") descricao: String, @Field("latitude") latitude: Double?,
                       @Field("longitude") longitude: Double?, @Field("utilizador_id") utilizador_id: Int?) : Call<OutputOcorrencia>

    @GET("api/ocorrenciadescricao")
    fun getOcorrenciaDescricao(): Call<List<Ocorrencia>>
}