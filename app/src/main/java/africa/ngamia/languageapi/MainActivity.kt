package africa.ngamia.languageapi

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var mBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadLocate() // call LoadLocate

        val actionBar = supportActionBar
        actionBar!!.title = resources.getString(R.string.app_name)

        mBtn = findViewById(R.id.mChangeLang)

        mBtn.setOnClickListener {
            showChangeLang()
        }
    }

    private fun showChangeLang() {

        val listItems = arrayOf("عربي", "हिंदी", "اردو", "English", "French", "Swahili", "Amharic")

        val mBuilder = AlertDialog.Builder(this@MainActivity)
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(listItems, -1) { dialog, which ->
            when (which) {
                0 -> {
                    setLocate("ar")
                    recreate()
                }
                1 -> {
                    setLocate("hi")
                    recreate()
                }
                2 -> {
                    setLocate("ur")
                    recreate()
                }
                3 -> {
                    setLocate("en")
                    recreate()
                }
                4 -> {
                    setLocate("fr")
                    recreate()
                }
                5 -> {
                    setLocate("sw")
                    recreate()
                }
                6 -> {
                    setLocate("am")
                    recreate()
                }
            }

            dialog.dismiss()
        }
        val mDialog = mBuilder.create()

        mDialog.show()

    }

    private fun setLocate(Lang: String) {

        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    private fun loadLocate() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        if (language != null) {
            setLocate(language)
        }
    }

}