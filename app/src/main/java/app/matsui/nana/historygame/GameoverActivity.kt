package app.matsui.nana.historygame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_gameover.*

class GameoverActivity : AppCompatActivity(){

    var answers = arrayOf(Triple("1560","桶狭間の戦い","織田信長が今川義元を破る"), Triple("1575","長篠の戦い","織田信長が鉄砲を有効活用して武田勝頼を破る"),
        Triple("1582","本能寺の変","織田信長が家臣の明智光秀に背かれ本能寺で自害"), Triple("1600","関ヶ原の戦い","徳川家康が石田三成を破る"),
        Triple("663","白村江の戦い","百済救助に失敗"),Triple("672","壬申の乱","天智天皇の後続者争い"),
        Triple("935","平将門の乱","関東で起こった反乱"), Triple("939","藤原純友の乱","瀬戸内海で起こった反乱"),
        Triple("1716","享保の改革","8代将軍 徳川吉宗が行なった改革"), Triple("1772","田沼意次の政治","老中 田沼意次が行なった改革"),
        Triple("1787","寛政の改革","老中 松平定信が行なった改革"), Triple("1814","天保の改革","老中 水野忠邦が行なった改革"),
        Triple("604","冠位十二階","聖徳太子が行った法令",),Triple("604","十七条の憲法","聖徳太子が出した法令"),
        Triple("701","大宝律令","唐を見本にして作られた"),Triple("743","墾田永年私財法","自分で開墾したら、永久に自分のものとしてよいという法令"),
        Triple("1297","永仁の徳政令","元寇により困窮する御家人の救済のための法令"),Triple("1536","太閤検地","豊臣秀吉が行った田畑の面積を調べて出した法令"),
        Triple("1588","刀狩令","豊臣秀吉が出した武器を取り上げる法令"),Triple("1615","武家諸法度","武士に向けて出された法令"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameover)

        val event0 = intent.getStringExtra("card0")
        val event1 = intent.getStringExtra("card1")
        val event2 = intent.getStringExtra("card2")
        val event3 = intent.getStringExtra("card3")
        val score = intent.getIntExtra("score",0)

        var answer0 = answers.filter { it.second ==  event0 }[0]
        var answer1 = answers.filter { it.second ==  event1 }[0]
        var answer2 = answers.filter { it.second ==  event2 }[0]
        var answer3 = answers.filter { it.second ==  event3 }[0]

        eventText0.text = answer0.first
        yearText0.text = answer0.second
        contentText0.text = answer0.third
        eventText1.text = answer1.first
        yearText1.text = answer1.second
        contentText1.text = answer1.third
        eventText2.text = answer2.first
        yearText2.text = answer2.second
        contentText2.text = answer2.third
        eventText3.text = answer3.first
        yearText3.text = answer3.second
        contentText3.text = answer3.third

        println(event0)

        back.setOnClickListener{
            val firstPage = Intent(this, FirstActivity::class.java)
            startActivity(firstPage)
        }

        registerPageButton.setOnClickListener{
            val registerPage = Intent(this, RegisterActivity::class.java)
            registerPage.putExtra("score", score)
            startActivity(registerPage)
        }
    }
}