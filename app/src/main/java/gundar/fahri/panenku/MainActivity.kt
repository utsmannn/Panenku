package gundar.fahri.panenku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import gundar.fahri.panenku.fragment.ItemListFragment
import gundar.fahri.panenku.fragment.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // definisikan fragment
        val itemsFragment = ItemListFragment()
        val newsFragment = NewsFragment()

        // definisikan adapter viewpager sebagai konektor fragment ke activity
        val mainPagerAdapter = MainPagerAdapter(supportFragmentManager)
        // menambah fragment ke dalam adapter
        mainPagerAdapter.addFragments(itemsFragment, newsFragment)


        // seting adapter ke dalam viewpager di activity
        main_viewpager.adapter = mainPagerAdapter
    }
}
