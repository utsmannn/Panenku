package gundar.fahri.panenku

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_main_page.view.*

class MainAdapter(private val listPetani: MutableList<Petani>) : RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_page, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int = listPetani.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val petani = listPetani[position]
        holder.bind(petani)
    }
}

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    @SuppressLint("SetTextI18n")
    fun bind(petani: Petani) = itemView.run {
        nama_petani.text = "Nama Petani: " + petani.namaPetani
        asal_petani.text = "Daerah Asal: " + petani.daerahAsalPetani
        jenis_tanaman.text = "Jenis Tanaman: " + petani.jenisTanaman

        Glide.with(context)
                .load(petani.gambarTanaman)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(img_view_item)

        ic_action_phone.setOnClickListener {
            val intentCall = Intent(Intent.ACTION_DIAL)
            intentCall.data = Uri.parse("tel:${petani.kontak}")
            context.startActivity(intentCall)
        }

        setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("data_petani", petani)
            context.startActivity(intent)
        }
    }
}