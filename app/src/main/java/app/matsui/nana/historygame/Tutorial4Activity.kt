package app.matsui.nana.historygame

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.blank1
import kotlinx.android.synthetic.main.activity_main.blank2
import kotlinx.android.synthetic.main.activity_main.blank3
import kotlinx.android.synthetic.main.activity_main.blank4
import kotlinx.android.synthetic.main.activity_main.card1
import kotlinx.android.synthetic.main.activity_main.card2
import kotlinx.android.synthetic.main.activity_main.card3
import kotlinx.android.synthetic.main.activity_main.card4
import kotlinx.android.synthetic.main.activity_main.desideButton
import kotlinx.android.synthetic.main.activity_main.textView2
import kotlinx.android.synthetic.main.activity_tutorial4.*

class Tutorial4Activity : AppCompatActivity() {

    var selectBlankIndex = ""
    var selectCardIndex = ""
    var correctCount = 0
    var score = 3
    var mistake = 3
    var blank = 0
    var card = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial4)


        blank1.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        blank2.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        blank3.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        blank4.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        card1.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        card2.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        card3.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        card4.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))

        textView2.text = "スコア" + score

        var questions = arrayOf("享保の改革", "田沼意次の政治","寛政の改革","天保の改革")

        card1.text = questions[0]
        card2.text = questions[2]
        card3.text = questions[3]
        card4.text = questions[1]

        var red = ColorStateList.valueOf(Color.rgb(212, 194, 252))
        var gray = ColorStateList.valueOf(Color.rgb(166, 181, 206))

        blank3.text = questions[2]
        blank1.text = questions[0]
        blank4.text = questions[3]


        blank2.setOnClickListener {
            blank2.backgroundTintList = red
            blank1.backgroundTintList = gray
            blank3.backgroundTintList = gray
            blank4.backgroundTintList = gray


            blank = 1
        }


        card4.setOnClickListener {
            card4.backgroundTintList = red
            card1.backgroundTintList = gray
            card2.backgroundTintList = gray
            card3.backgroundTintList = gray

            card = 1
        }

        desideButton.setOnClickListener {
            if (blank == 1) {
                if (card == 1) {
                    score == score + 1
                    blank2.text = questions[1]
                    blank2.backgroundTintList = ColorStateList.valueOf(Color.rgb(184, 192, 255))
                    card4.backgroundTintList = ColorStateList.valueOf(Color.rgb(184, 192, 255))
                    val tutorial5Page = Intent(this, Tutorial5Activity::class.java)
                    startActivity(tutorial5Page)
                }
            }

        }


    }

    override fun onResume() {
        super.onResume()
        correctCount = 0


        var questions = arrayOf("享保の改革", "田沼意次の政治","寛政の改革","天保の改革")

        blank3.text = questions[2]
        blank1.text = questions[0]
        blank4.text = questions[3]
        blank2.text = "⬇︎"

        card1.text = questions[0]
        card2.text = questions[2]
        card3.text = questions[3]
        card4.text = questions[1]


        blank1.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        blank2.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        blank3.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        blank4.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        card1.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        card2.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        card3.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        card4.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
    }
}
