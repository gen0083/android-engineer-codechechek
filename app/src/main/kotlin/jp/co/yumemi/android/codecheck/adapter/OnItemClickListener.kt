package jp.co.yumemi.android.codecheck.adapter

import jp.co.yumemi.android.codecheck.api.RepositoryInfo

interface OnItemClickListener {
    fun itemClick(item: RepositoryInfo)
}