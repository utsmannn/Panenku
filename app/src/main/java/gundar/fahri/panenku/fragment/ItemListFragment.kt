package gundar.fahri.panenku.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import gundar.fahri.panenku.*
import kotlinx.android.synthetic.main.fragment_list_item.*

class ItemListFragment : Fragment() {

    private val listPetani: MutableList<Petani> = mutableListOf()
    private val adapter = MainAdapter(listPetani)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databasePetani = FirebaseDatabase.getInstance()
        val petaniRef = databasePetani.getReference("main_database")

        recyclerview_petani.layoutManager = LinearLayoutManager(requireContext())
        recyclerview_petani.adapter = adapter

        petaniRef.addValueEventListener(listenerFirebaseMain())

        ic_action_add_person.setOnClickListener {
            addPerson()
        }
    }

    private fun listenerFirebaseMain() = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {
            Log.e("error anjir", error.message)
        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            Log.i("anjir", dataSnapshot.toString())

            for (data in dataSnapshot.children) {
                val namaPetani = data.child("nama_petani").getValue(String::class.java)
                val daerahAsalPetani = data.child("daerah_asal").getValue(String::class.java)
                val jenisTanaman = data.child("jenis_tanaman").getValue(String::class.java)
                val jumlahTanaman = data.child("jumlah_tanaman").getValue(Int::class.java)
                val gambarTanaman = data.child("gambar_url").getValue(String::class.java)
                val nomerKontak = data.child("kontak").getValue(String::class.java)
                val id = data.child("id").getValue(String::class.java)

                val petani = Petani(namaPetani, daerahAsalPetani, jenisTanaman, jumlahTanaman, gambarTanaman, id, nomerKontak)
                listPetani.add(petani)
                adapter.notifyDataSetChanged()
            }
        }

    }

    private fun addPerson() {
        val intentNewPetani = Intent(context, FormNewPetaniActivity::class.java)
        context?.startActivity(intentNewPetani)
        activity?.finish()
    }
}