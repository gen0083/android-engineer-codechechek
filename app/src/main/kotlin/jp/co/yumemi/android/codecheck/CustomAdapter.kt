package jp.co.yumemi.android.codecheck

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    private val itemClickListener: OnItemClickListener,
) : ListAdapter<RepositoryInfo, CustomAdapter.ViewHolder>(object :
    DiffUtil.ItemCallback<RepositoryInfo>() {
    override fun areItemsTheSame(
        oldRepositoryInfo: RepositoryInfo,
        newRepositoryInfo: RepositoryInfo,
    ): Boolean {
        return oldRepositoryInfo.name == newRepositoryInfo.name
    }

    override fun areContentsTheSame(
        oldRepositoryInfo: RepositoryInfo,
        newRepositoryInfo: RepositoryInfo,
    ): Boolean {
        return oldRepositoryInfo == newRepositoryInfo
    }
}) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameText = view.findViewById<TextView>(R.id.repositoryNameView)

        fun bind(repositoryInfo: RepositoryInfo) {
            nameText.text = repositoryInfo.name
        }
    }

    interface OnItemClickListener {
        fun itemClick(item: RepositoryInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            itemClickListener.itemClick(item)
        }
    }
}