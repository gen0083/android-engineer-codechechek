package jp.co.yumemi.android.codecheck

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class RepositoryInfo(
    @SerialName("full_name")
    val name: String,
    val owner: RepositoryOwner,
    @SerialName("language")
    val language: String? = "unknown",
    @SerialName("stargazers_count")
    val stargazersCount: Long,
    @SerialName("watchers_count")
    val watchersCount: Long,
    @SerialName("forks_count")
    val forksCount: Long,
    @SerialName("open_issues_count")
    val openIssuesCount: Long,
) : Parcelable

@Parcelize
@Serializable
data class RepositoryOwner(
    @SerialName("avatar_url")
    val ownerIconUrl: String,
) : Parcelable

@Serializable
data class RepositorySearchResponse(
    @SerialName("total_count")
    val count: Int,
    val items: List<RepositoryInfo>,
)