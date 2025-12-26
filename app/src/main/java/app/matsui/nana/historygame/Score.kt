package app.matsui.nana.historygame

import com.google.firebase.firestore.DocumentId
import java.util.*

data class Score(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val score: Int = 0,
    var createdAt: Date = Date(System.currentTimeMillis()),
)

