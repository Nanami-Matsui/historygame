package app.matsui.nana.historygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_tutorial5.*

class Tutorial5Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial5)
        Log.d("fff", "fffffffffffffffff"
        )

        registerPageButton.setOnClickListener{
            Log.d("intent","OK")
            val tutorial6Page = Intent(this, Tutorial6Activity::class.java)
            startActivity(tutorial6Page)
        }
    }
}

