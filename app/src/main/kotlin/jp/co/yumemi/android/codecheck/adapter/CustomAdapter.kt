package jp.co.yumemi.android.codecheck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import jp.co.yumemi.android.codecheck.R
import jp.co.yumemi.android.codecheck.RepositoryInfo

class CustomAdapter(
    private val itemClickListener: OnItemClickListener,
) : ListAdapter<RepositoryInfo, ViewHolder>(object :
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