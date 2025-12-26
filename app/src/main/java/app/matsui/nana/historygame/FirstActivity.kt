package app.matsui.nana.historygame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_first.*


class FirstActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        startButton.setOnClickListener {
            val mainPage = Intent(this, MainActivity::class.java)
            startActivity(mainPage)
        }

        rankingButton.setOnClickListener {
            val scorePage = Intent(this, ScoreActivity::class.java)
            startActivity(scorePage)
        }

        tutorialButton.setOnClickListener {
            Log.d("tutorial","ボタンが押された")
            val tutorialPage = Intent(this, TutorialActivity::class.java)
            startActivity(tutorialPage)
        }

        //timeButton.setOnClickListener {
            //val timePage = Intent(this, TimeMainActivity::class.java)
            //startActivity(timePage)
        //}


        //movieButton.setOnClickListener {
            //val intent= Intent(application, TutorialActivity::class.java)
            //startActivity(intent)
        //}
    }
}