package app.matsui.nana.historygame

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val score = intent.getIntExtra("score",0)

        textView6.text = "スコア　" + score

        register.backgroundTintList = ColorStateList.valueOf(Color.rgb(221, 192, 232))
        back2.backgroundTintList = ColorStateList.valueOf(Color.rgb(221, 192, 232))

        register.setOnClickListener{

            register.backgroundTintList = ColorStateList.valueOf(Color.rgb(212, 194, 252))

            val task = Score(
                score = score.toInt(),
                name = name.text.toString()
            )


            val db = Firebase.firestore
            db.collection("score")
                    .add(task)
                    .addOnSuccessListener { documentReference ->
                        Log.d("ADD_TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.d("ADD_TAG", "Error adding document", e)
                    }
            Log.d("ADD_TAG", "d")
        }

        back2.setOnClickListener{
            val back = Intent(this, FirstActivity::class.java)
            startActivity(back)
        }
    }
}