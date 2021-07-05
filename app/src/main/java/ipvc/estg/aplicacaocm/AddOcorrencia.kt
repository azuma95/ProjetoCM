package ipvc.estg.aplicacaocm

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.android.gms.location.*
import ipvc.estg.aplicacaocm.api.EndPoints
import ipvc.estg.aplicacaocm.api.ServiceBuilder

class AddOcorrencia : AppCompatActivity() {

    private lateinit var editDescricao : EditText
    private var latitude : Double = 0.0
    private var longitude : Double = 0.0

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var lastLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ocorrencia)

        editDescricao = findViewById(R.id.edit_descricao)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

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
        val utilizador_id = 1
    }
}