package app.matsui.nana.historygame

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import app.matsui.nana.historygame.databinding.ActivityTimemainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_timemain.*
import kotlinx.android.synthetic.main.activity_score.*
import java.util.*
import app.matsui.nana.historygame.AnswerActivity as HistorygameAnswerActivity

class TimeMainActivity : AppCompatActivity() {

    var selectBlankIndex = ""
    var selectCardIndex = ""
    var correctCount = 0
    var score = 0
    var mistake = 3
    var questions = arrayOf(Pair(1560,"桶狭間の戦い"),Pair(1575,"長篠の戦い"),Pair(1582,"本能寺の変"),Pair(1600,"関ヶ原の戦い"), Pair(663,"白村江の戦い"),Pair(672,"壬申の乱"),Pair(935,"平将門の乱"),Pair(939,"藤原純友の乱"),
            Pair(1716,"享保の改革"),Pair(1772,"田沼意次の政治"),Pair(1787,"寛政の改革"),Pair(1814,"天保の改革"),Pair(604,"冠位十二階"),Pair(604,"十七条の憲法"),Pair(701,"大宝律令"),Pair(743,"墾田永年私財法"),
            Pair(1297,"永仁の徳政令"),Pair(1536,"太閤検地"),Pair(1588,"刀狩令"),Pair(1615,"武家諸法度"),Pair(1274,"文永の役"),Pair(1281,"弘安の役"),Pair(1853,"ペリー来航"),Pair(1868,"明治維新"), Pair(1894,"日清戦争"), Pair(1923,"関東大震災"))

    private lateinit var binding: ActivityTimemainBinding


    //var questionMap4: Map<Int, String> = mapOf(1 to "桶狭間の戦い", 2 to "長篠の戦い", 3 to "本能寺の変", 4 to "関ヶ原の戦い")

    //var questionArray = arrayOf(questionTatakaiMap,questionTatakaiMap1,questionKaikakuMap,questionHoureiMap,questionHoureiMap1)
    var selectCard = 0
    var selectBlank = 0
    var second = 30

    lateinit var answerBoxArray : Array<Button>
    lateinit var cardBoxArray : Array<Button>
    lateinit var questioning : List<Pair<Int,String>>
    lateinit var comparator  : Comparator<Pair<Int, String>>
    lateinit var questionOrder : List<Pair<Int,String>>

    //val timer: CountDownTimer = object : CountDownTimer(30000, 1000) {
        //override fun onFinish() {
            //Log.d("Finish", "Finish")
            //val gameoverPage = Intent(this@TimeMainActivity, GameoverActivity::class.java)
            //gameoverPage.putExtra("card0", questionOrder[0].second)
            //gameoverPage.putExtra("card1", questionOrder[1].second)
            //gameoverPage.putExtra("card2", questionOrder[2].second)
            //gameoverPage.putExtra("card3", questionOrder[3].second)
            //gameoverPage.putExtra("score", score)
            //startActivity(gameoverPage)
        //}
        //override fun onTick(p0: Long) {
            //Log.d("Count", second.toString())
            //second = second - 1
            //binding.timeText.text = second.toString()
        //}
        //override fun onTick(p0: Long) {
           //Log.d("Count", second.toString())
           //binding.timeText.text = second.toString()
            //second--
        //}
    //}
    val timer: CountDownTimer = object : CountDownTimer(30000, 1000) {
        override fun onFinish() {
            Log.d("Finish", "Finish")
            val gameoverPage = Intent(this@TimeMainActivity, GameoverActivity::class.java)
            gameoverPage.putExtra("card0", questionOrder[0].second)
            gameoverPage.putExtra("card1", questionOrder[1].second)
            gameoverPage.putExtra("card2", questionOrder[2].second)
            gameoverPage.putExtra("card3", questionOrder[3].second)
            gameoverPage.putExtra("score", score)
            startActivity(gameoverPage)
        }
        override fun onTick(p0: Long) {
            Log.d("Count", second.toString())
            binding.timeText.text = second.toString()
            second--
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timemain)
        binding = ActivityTimemainBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        binding.timeText.text = second.toString()

        answerBoxArray = arrayOf(blank1, blank2, blank3, blank4)
        cardBoxArray = arrayOf(card1, card2, card3, card4)

        questions.shuffle()

        for (i in 0..3){
            cardBoxArray[i].text = questions[i].second
        }

            blank1.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
            blank2.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
            blank3.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
            blank4.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
            card1.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
            card2.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
            card3.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
            card4.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))

        questioning = questions.take(4)
        comparator = compareBy { it.first }
        questionOrder = questioning.sortedWith(comparator)

        textView2.text = "スコア" + score

        timer.start()
        Log.d("Count","Start")

        var red = ColorStateList.valueOf(Color.rgb(212, 194, 252))
        var gray = ColorStateList.valueOf(Color.rgb(166, 181, 206))

        blank1.setOnClickListener {
            blank1.backgroundTintList = red
            blank2.backgroundTintList = gray
            blank3.backgroundTintList = gray
            blank4.backgroundTintList = gray

            selectBlankIndex = questionOrder[0].second
            selectBlank = 1
        }

        blank2.setOnClickListener {
            blank2.backgroundTintList = red
            blank1.backgroundTintList = gray
            blank3.backgroundTintList = gray
            blank4.backgroundTintList = gray

            selectBlankIndex = questionOrder[1].second
            selectBlank = 2
        }

        blank3.setOnClickListener {
            blank3.backgroundTintList = red
            blank1.backgroundTintList = gray
            blank2.backgroundTintList = gray
            blank4.backgroundTintList = gray


            selectBlankIndex = questionOrder[2].second
            selectBlank = 3
        }

        blank4.setOnClickListener {
            blank4.backgroundTintList = red
            blank1.backgroundTintList = gray
            blank2.backgroundTintList = gray
            blank3.backgroundTintList = gray

            selectBlankIndex = questionOrder[3].second
            selectBlank = 4

        }

        card1.setOnClickListener {
            card1.backgroundTintList = red
            card2.backgroundTintList = gray
            card3.backgroundTintList = gray
            card4.backgroundTintList = gray

            selectCardIndex = questions[0].second
            selectCard = 1

        }

        card2.setOnClickListener {
            card2.backgroundTintList = red
            card1.backgroundTintList = gray
            card3.backgroundTintList = gray
            card4.backgroundTintList = gray


            selectCardIndex = questions[1].second
            selectCard = 2
        }

        card3.setOnClickListener {
            card3.backgroundTintList = red
            card1.backgroundTintList = gray
            card2.backgroundTintList = gray
            card4.backgroundTintList = gray

            selectCardIndex = questions[2].second
            selectCard = 3

        }

        card4.setOnClickListener {
            card4.backgroundTintList = red
            card1.backgroundTintList = gray
            card2.backgroundTintList = gray
            card3.backgroundTintList = gray

            selectCardIndex = questions[3].second
            selectCard = 4
        }

        scoreRisetButton.setOnClickListener {
            score = 0
            textView2.text = "スコア 0"
        }

        desideButton.setOnClickListener {
            questions.take(4)
            var questioning = questions.take(4)
            if (selectBlankIndex == selectCardIndex) {
                answerBoxArray[selectBlank - 1].text = selectCardIndex
                answerBoxArray[selectBlank - 1].backgroundTintList = ColorStateList.valueOf(Color.rgb(184,192,255))
                cardBoxArray[selectCard - 1].backgroundTintList = ColorStateList.valueOf(Color.rgb(184,192,255))
                //answerBoxArray[selectBlank - 1].backgroundTintList = ColorStateList.valueOf(Color.rgb(254, 147, 140))
                //cardBoxArray[selectCard - 1].backgroundTintList = ColorStateList.valueOf(Color.rgb(254, 147, 140))
                correctCount = correctCount + 1
                score = score + 1

                textView2.text = "スコア" + score

                if (correctCount == 4) {
                    timer.cancel()
                    val answerPage = Intent(this, HistorygameAnswerActivity::class.java)
                    answerPage.putExtra("card0", questionOrder[0].second)
                    answerPage.putExtra("card1", questionOrder[1].second)
                    answerPage.putExtra("card2", questionOrder[2].second)
                    answerPage.putExtra("card3", questionOrder[3].second)
                    answerPage.putExtra("score", score)
                    startActivity(answerPage)
                }

            } else {
                mistake = mistake - 1

                answerBoxArray[selectBlank - 1].backgroundTintList = ColorStateList.valueOf(Color.rgb(254, 147, 140))
                cardBoxArray[selectCard - 1].backgroundTintList = ColorStateList.valueOf(Color.rgb(254, 147, 140))

                if (mistake == 2) {
                    textView8.text = "♡♡"
                }else if (mistake == 1) {
                    textView8.text = "♡"
                }else if (mistake == 0) {
                    timer.cancel()
                    val gameoverPage = Intent(this, GameoverActivity::class.java)
                            gameoverPage.putExtra("card0", questionOrder[0].second)
                            gameoverPage.putExtra("card1", questionOrder[1].second)
                            gameoverPage.putExtra("card2", questionOrder[2].second)
                            gameoverPage.putExtra("card3", questionOrder[3].second)
                            gameoverPage.putExtra("score", score)
                    startActivity(gameoverPage)
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        correctCount = 0
        lateinit var binding: ActivityTimemainBinding
        second = 30

        questions.shuffle()

        questioning = questions.take(4)
        comparator = compareBy { it.first }
        questionOrder = questioning.sortedWith(comparator)

        blank1.text = "古"
        blank2.text = "⬇︎"
        blank3.text = "⬇︎"
        blank4.text = "新"

        card1.text = ""
        card2.text = ""
        card3.text = ""
        card4.text = ""

        for (i in 0..3){
            cardBoxArray[i].text = questions[i].second
        }

        blank1.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        blank2.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        blank3.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        blank4.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        card1.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        card2.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        card3.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))
        card4.backgroundTintList = ColorStateList.valueOf(Color.rgb(166, 181, 206))

        timer.start()
    }
}