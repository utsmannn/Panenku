package gundar.fahri.panenku

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val listFragment: MutableList<Fragment> = mutableListOf()

    override fun getItem(position: Int): Fragment = listFragment[position]

    override fun getCount(): Int = listFragment.size

    fun addFragments(vararg fragments: Fragment) {
        fragments.map { frg ->
            listFragment.add(frg)
        }
    }
}