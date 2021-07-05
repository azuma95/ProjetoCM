package ipvc.estg.aplicacaocm.api

data class OutputOcorrencia (
    val latitude: Double,
    val longitude: Double,
    val descricao: String,
    val utilizador_id: Int,
    val status: String,
    val MSG: String
)