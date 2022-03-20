package com.jydev.data

import androidx.paging.PagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

suspend fun <PaginationKey: Any, Model: Any>PagingSource<PaginationKey, Model>.getData(): List<Model> {
    val data = mutableListOf<Model>()
    val job = CoroutineScope(Dispatchers.Main).launch {
        val loadResult: PagingSource.LoadResult<PaginationKey, Model> = load(
            PagingSource.LoadParams.Refresh(
                key = null, loadSize = Int.MAX_VALUE, placeholdersEnabled = false
            )
        )
        when (loadResult) {
            is PagingSource.LoadResult.Error -> throw loadResult.throwable
            is PagingSource.LoadResult.Page -> data.addAll(loadResult.data)
        }
    }
    job.join()
    return data
}