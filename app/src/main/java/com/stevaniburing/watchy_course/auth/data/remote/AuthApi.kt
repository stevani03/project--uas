package com.stevaniburing.watchy_course.auth.data.remote

import com.stevaniburing.watchy_course.auth.data.remote.dto.AuthRequest
import com.stevaniburing.watchy_course.auth.data.remote.dto.AuthRespond
import com.stevaniburing.watchy_course.util.BackendConstants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * @author Ahmed Guedmioui
 */
interface AuthApi {

    @POST(BackendConstants.REGISTER)
    suspend fun register(
        @Body authRequest: AuthRequest
    )

    @POST(BackendConstants.LOGIN)
    suspend fun login(
        @Body authRequest: AuthRequest
    ): AuthRespond

    @GET(BackendConstants.AUTHENTICATE)
    suspend fun authenticate(
        @Header("Authorization") token: String
    )

}
















