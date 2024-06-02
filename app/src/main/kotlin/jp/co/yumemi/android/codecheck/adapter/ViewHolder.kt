package jp.co.yumemi.android.codecheck.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jp.co.yumemi.android.codecheck.R
import jp.co.yumemi.android.codecheck.api.RepositoryInfo

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nameText = view.findViewById<TextView>(R.id.repositoryNameView)

    fun bind(repositoryInfo: RepositoryInfo) {
        nameText.text = repositoryInfo.name
    }
}