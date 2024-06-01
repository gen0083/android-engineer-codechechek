package jp.co.yumemi.android.codecheck

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nameText = view.findViewById<TextView>(R.id.repositoryNameView)

    fun bind(repositoryInfo: RepositoryInfo) {
        nameText.text = repositoryInfo.name
    }
}