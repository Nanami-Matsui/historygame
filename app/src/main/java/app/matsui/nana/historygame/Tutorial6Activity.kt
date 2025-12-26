package app.matsui.nana.historygame

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_tutorial6.*

class Tutorial6Activity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial6)

        register2.setOnClickListener{
            register2.backgroundTintList = ColorStateList.valueOf(Color.rgb(212, 194, 252))

            val tutorial7Page = Intent(this, Tutorial7Activity::class.java)
            startActivity(tutorial7Page)
        }


    }
}