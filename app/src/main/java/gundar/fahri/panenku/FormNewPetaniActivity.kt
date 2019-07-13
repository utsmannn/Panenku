package gundar.fahri.panenku

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_form_add_person.*

class FormNewPetaniActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_add_person)

        btn_save_new_person.setOnClickListener {
            saveNewPerson()
        }
    }

    private fun saveNewPerson() {

        val databasePetani = FirebaseDatabase.getInstance()
        val petaniRef = databasePetani.getReference("main_database")

        val namaPetani = input_nama_petani.text.toString()
        val daerahAsal = input_daerah_asal.text.toString()
        val jenisTanaman = input_jenis_tanaman.text.toString()
        val jumlahTanaman = input_jumlah_tanaman.text.toString()
        val gambarUrl = input_url_img.text.toString()
        val id = input_nama_petani.text.toString().toLowerCase()
        val kontak = input_kontak.text.toString()

        petaniRef.child(id).apply {
            child("daerah_asal").setValue(daerahAsal)
            child("gambar_url").setValue(gambarUrl)
            child("id").setValue(id)
            child("jenis_tanaman").setValue(jenisTanaman)
            child("jumlah_tanaman").setValue(jumlahTanaman.toInt())
            child("kontak").setValue(kontak)
            child("nama_petani").setValue(namaPetani)
        }

        Toast.makeText(this, "Data Telah Di Simpan", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({
            finish()
            onBackPressed()
        }, 500)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}