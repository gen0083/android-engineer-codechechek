package jp.co.yumemi.codecheck.di

import jp.co.yumemi.codecheck.ui.detail.RepositoryDetailScreenModel
import jp.co.yumemi.codecheck.ui.search.SearchScreenModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val screenModelModule = module {
    factoryOf(::SearchScreenModel)
    factoryOf(::RepositoryDetailScreenModel)
}
