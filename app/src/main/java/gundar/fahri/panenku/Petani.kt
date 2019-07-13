package gundar.fahri.panenku

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Petani(val namaPetani: String?,
                  val daerahAsalPetani: String?,
                  val jenisTanaman: String?,
                  val jumlahTanaman: Int?,
                  val gambarTanaman: String?,
                  val id: String?,
                  val kontak: String?) : Parcelable