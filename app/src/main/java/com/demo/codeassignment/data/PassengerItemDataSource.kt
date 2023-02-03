package com.demo.codeassignment.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.demo.codeassignment.base.BaseResponse
import com.demo.codeassignment.data.models.PhotosList
import com.demo.codeassignment.data.repository.Repository

class PassengerItemDataSource(private val dataRepository: Repository
) : PagingSource<Int, PhotosList>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotosList> {
        val position = params.key ?: STARTING_PAGE_INDEX
        //Retrofit will automatically make suspend functions main-safe so you can call them directly from Dispatchers.Main
        return when (val result = dataRepository.getpicsumlist(position, params.loadSize)) {

            is BaseResponse.Error ->{
                LoadResult.Error(Exception(""))
            }
            is BaseResponse.Error -> LoadResult.Error(Exception())
            is BaseResponse.Success -> {
                LoadResult.Page(
                    data = result.data,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (result.data.isEmpty()) null else position + 1
                )
            }
        }

    }

    @ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<Int, PhotosList>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}