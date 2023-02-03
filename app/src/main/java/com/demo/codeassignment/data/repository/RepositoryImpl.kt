package com.demo.codeassignment.data.repository

import com.demo.codeassignment.base.BaseResponse
import com.demo.codeassignment.common.*
import com.demo.codeassignment.data.models.*
import com.demo.codeassignment.data.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val errorHandler: ErrorHandler
) : Repository {

    override suspend fun getpicsumlist(page: Int , limit: Int): BaseResponse<List<PhotosList>> {
        return try {
            val dateandtime = apiService.getpicsumlist(page, limit)
            BaseResponse.Success(dateandtime)
        } catch (e: Exception) {
            val errorType = errorHandler.getErrorType(e)
            BaseResponse.Error(errorType)
        }
    }
}