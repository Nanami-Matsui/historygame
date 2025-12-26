package app.matsui.nana.historygame

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tutorial7.*

class Tutorial7Activity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial7)

        back5.setOnClickListener{
            val firstPage = Intent(this, FirstActivity::class.java)
            startActivity(firstPage)
        }


    }
}