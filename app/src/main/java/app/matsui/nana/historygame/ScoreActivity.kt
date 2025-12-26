package app.matsui.nana.historygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_answer.*
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_score.*
import java.lang.reflect.Array.get
import java.util.ArrayList

class ScoreActivity : AppCompatActivity() {

    val scoreData : List<ScoreData> = listOf(
        ScoreData("1","player1","score1"),
        ScoreData("2","player2","score2"),
        ScoreData("3","player3","score3")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val adapter = RecyclerViewAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val db = Firebase.firestore

        // アプリ起動時に、保存されているデータを取得する
        db.collection("score")
            .get()
            .addOnSuccessListener { score ->
               val scoreList = ArrayList<Score>()
                score.forEach { scoreList.add(it.toObject(Score::class.java)) }
                Log.d("scoreList", scoreList.toString())
                adapter.addAll(scoreList)
            }


            //.addOnCompleteListener{task ->
              //  if(task.isSuccessful){
                //    val document = task.result
                  //  if(document != null && document.toObjects(Score::class.java)!= null){
                    //    val scoreList = document.toObjects(Score::class.java)
                      //  Log.d("scoreListSize", scoreList.size.toString())
                        //for(i in 0 until scoreList.size)
                          //  Log.d("name", scoreList.get(i).name)
                    //}
                //}
            //}


            //.addOnSuccessListener { score ->
             //   val scoreList = ArrayList<Score>()
                //score.forEach { scoreList.add(it.toObject(Score::class.java)) }
             //   Log.d("scoreList", scoreList.toString())
             //   adapter.addAll(scoreList)
            //}
            .addOnFailureListener { exception ->
                Log.d("ADD_TAG", "Error adding document", exception)

            }

        backButton.setOnClickListener {
            val firstPage = Intent(this, FirstActivity::class.java)
            startActivity(firstPage)
        }
    }
}