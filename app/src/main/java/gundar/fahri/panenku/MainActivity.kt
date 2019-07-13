package gundar.fahri.panenku

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.utsman.easygooglelogin.EasyGoogleLogin
import com.utsman.easygooglelogin.LoginResultListener
import gundar.fahri.panenku.fragment.ItemListFragment
import gundar.fahri.panenku.fragment.NewsFragment
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), LoginResultListener {

    private val googleLogin by lazy {
        EasyGoogleLogin(this)
    }

    private val token by lazy {
        getString(R.string.default_web_client_id)
    }

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


        googleLogin.initGoogleLogin(token, this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_logout -> logout()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        googleLogin.signOut(this)
    }

    override fun onStart() {
        super.onStart()
        googleLogin.initOnStart()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        googleLogin.onActivityResult(this, requestCode, data)
    }

    override fun onLoginSuccess(user: FirebaseUser?) {
    }

    override fun onLoginFailed(exception: Exception?) {
        Toast.makeText(this, "login anda gagal", Toast.LENGTH_SHORT).show()
        Log.e("lllllll", exception?.localizedMessage)
    }

    override fun onLogoutError(exception: Exception?) {
        Toast.makeText(this, "Logout Gagal", Toast.LENGTH_SHORT).show()
        Log.e("Logout gagal", exception?.message)
    }

    override fun onLogoutSuccess(task: Task<Void>?) {
        Toast.makeText(this, "Logout Berhasil", Toast.LENGTH_SHORT).show()
        finish()
    }
}
