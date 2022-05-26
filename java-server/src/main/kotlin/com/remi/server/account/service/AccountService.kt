package com.remi.server.account.service

import com.remi.grpc.account.GetRequest
import com.remi.grpc.account.GetResponse
import com.remi.grpc.account.AccountServiceGrpcKt.AccountServiceCoroutineImplBase
import com.remi.grpc.account.Response
import com.remi.server.entity.Account
import com.remi.server.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import net.devh.boot.grpc.server.service.GrpcService
import java.util.*

@GrpcService
class AccountService() : AccountServiceCoroutineImplBase() {

    @Autowired
    private lateinit var repo: AccountRepository

    override suspend fun get(request: GetRequest): GetResponse {
        val builder = Response.newBuilder()
        val response = GetResponse.newBuilder()
        try {
            var optional: Optional<Account> = if(request.keyword.isNullOrEmpty()) {
                repo.findById(request.id)
            } else {
                repo.findByIdAndName(request.id, request.keyword)
            }

            if(optional.isPresent) {
                var account = optional.get()
                response
                    .setResp(builder.setSuccess(true))
                    .setId(account.id)
                    .setType(account.type)
                    .setName(account.name)
                    .setDescription(account.description)
                    .setCreatedAt(account.createdAt.toString())
                    .setUpdatedAt(account.updatedAt.toString())
            } else {
                response.setResp(
                    builder.setSuccess(false).setErrorMessage("Account not found")
                )
            }
        }
        catch(ex: Exception) {
            response.setResp(
                builder.setSuccess(false).setErrorMessage(ex.message)
            )
        }

        return response.build()
    }
}