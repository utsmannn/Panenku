package gundar.fahri.panenku

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.utsman.easygooglelogin.EasyGoogleLogin
import com.utsman.easygooglelogin.LoginResultListener
import kotlinx.android.synthetic.main.activity_landing.*
import java.lang.Exception

class LoginActivity : AppCompatActivity(), LoginResultListener {

    private val googleLogin by lazy {
        EasyGoogleLogin(this)
    }

    private val token by lazy {
        getString(R.string.default_web_client_id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        googleLogin.initGoogleLogin(token, this)
        btn_login.setOnClickListener {
            googleLogin.signIn(this)
        }
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
        Toast.makeText(this, "Anda login sebagai ${user?.displayName}", Toast.LENGTH_SHORT).show()

        btn_login.visibility = View.GONE

        Handler().postDelayed({

            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }, 1500)
    }

    override fun onLoginFailed(exception: Exception?) {
        Toast.makeText(this, "login anda gagal", Toast.LENGTH_SHORT).show()
        Log.e("lllllll", exception?.localizedMessage)
    }

    override fun onLogoutError(exception: Exception?) {

    }

    override fun onLogoutSuccess(task: Task<Void>?) {

    }
}