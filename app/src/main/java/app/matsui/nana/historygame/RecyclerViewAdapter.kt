package app.matsui.nana.historygame

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.*
import java.util.*
import kotlin.Comparator
import kotlin.collections.LinkedHashMap

class RecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    val items: MutableList<Score> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rankTextView: TextView = view.findViewById(R.id.rankTextView)
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val scoreTextView: TextView = view.findViewById(R.id.scoreTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_score_data_cell, parent, false)
            return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val ranking = getRankingMap(items.map { it.score })
        holder.rankTextView.text = ranking?.get(item.score).toString()
        holder.nameTextView.text = item.name
        holder.scoreTextView.text = item.score.toString()
    }

    fun addAll(items: List<Score>) {
        val comparator : Comparator<Score> = compareBy { it.score }
        val sortedItems = items.sortedWith(comparator).reversed()
        this.items.addAll(sortedItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getRankingMap(points: List<Int>): Map<Int, Int>? {

        // ソート（昇順）
        val len = points.size
        val temp = IntArray(len)
        for (i in 0 until len) {
            temp[i] = points[i]
        }
        Arrays.sort(temp)

        // 降順にする
        val pointsDesc = IntArray(len)
        for (i in 0 until len) {
            pointsDesc[i] = temp[len - 1 - i]
        }

        // ランク付け
        val map: MutableMap<Int, Int> = LinkedHashMap()
        var rank = 1
        map[pointsDesc[0]] = rank
        for (i in 1 until len) {
            if (pointsDesc[i] != pointsDesc[i - 1]) {
                // 点数が前の人と違うなら、通し番号を設定
                rank = i + 1
            }
            map[pointsDesc[i]] = rank
        }
        return map
    }
}