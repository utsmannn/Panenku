package gundar.fahri.panenku

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val petani = intent.getParcelableExtra<Petani>("data_petani")

        Glide.with(this)
                .load(petani.gambarTanaman)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(img_view_item)

        jenis_tanaman.text = petani.jenisTanaman
        jumlah_tanaman.text = petani.jumlahTanaman.toString() + " Kg"

        nama_petani.text = "Nama Petani: " + petani.namaPetani
        daerah_asal.text = "Daerah Asal: " + petani.daerahAsalPetani

        kontak_petani.text = petani.kontak

        ic_action_phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + petani.kontak)
            startActivity(intent)
        }
    }
}