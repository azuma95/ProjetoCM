package ipvc.estg.aplicacaocm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import ipvc.estg.aplicacaocm.api.EndPoints
import ipvc.estg.aplicacaocm.api.Ocorrencia
import ipvc.estg.aplicacaocm.api.OutputOcorrencia
import ipvc.estg.aplicacaocm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class AddOcorrencia : AppCompatActivity() {

    private lateinit var editDescricao : EditText
    private var latitude : Double = 0.0
    private var longitude : Double = 0.0

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var lastLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ocorrencia)

        editDescricao = findViewById(R.id.edit_descricao)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        //Vai buscar o sharedPrefernces anterior e pega no id do Utilizador Atual
        sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)

        locationCallback = object : LocationCallback(){
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                lastLocation = p0?.lastLocation!!
                latitude = lastLocation.latitude
                longitude = lastLocation.longitude
            }
        }
        createLocationRequest()
    }

    private fun createLocationRequest(){
        locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    fun adicionarOcorrencia(view : View){
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val latitude = latitude
        val longitude = longitude
        val descricao = editDescricao.text.toString()
        val utilizador_id = sharedPreferences.getInt("id", 1)

        val call = request.postOcorrencia(latitude = latitude, longitude = longitude, descricao = descricao, utilizador_id = utilizador_id)

        call.enqueue(object : Callback<OutputOcorrencia> {
            override fun onResponse(call: Call<OutputOcorrencia>, response: Response<OutputOcorrencia>){
                if(response.isSuccessful){
                    val c: OutputOcorrencia = response.body()!!
                    Toast.makeText(this@AddOcorrencia, c.MSG, Toast.LENGTH_LONG).show()
                    val intent = Intent(this@AddOcorrencia, MapsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            override fun onFailure(call: Call<OutputOcorrencia>, t: Throwable){
                Toast.makeText(this@AddOcorrencia, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }

    private fun startLocationUpdates() {
        if(ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)

            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)

    }
}