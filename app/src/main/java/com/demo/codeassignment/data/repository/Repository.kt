package com.demo.codeassignment.data.repository

import com.demo.codeassignment.base.BaseResponse
import com.demo.codeassignment.data.models.*

interface Repository{

    suspend fun getpicsumlist(page : Int, limit: Int ): BaseResponse<List<PhotosList>>

}