package com.remi.rest.service

import com.remi.grpc.account.GetRequest
import com.remi.grpc.account.AccountServiceGrpcKt.AccountServiceCoroutineStub
import com.remi.rest.data.AccountData
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service

@Service
class AccountService() {

    @GrpcClient("grpc-server")
    private var stub: AccountServiceCoroutineStub? = null

    suspend fun get(id: String, keyword: String?): AccountData? {
        val builder = GetRequest.newBuilder()
        builder.id = id
        if(keyword != null) {
            builder.keyword = keyword
        }
        val resp = stub?.get(builder.build())
        return resp?.let { AccountData(it.id, resp.name) }
    }
}