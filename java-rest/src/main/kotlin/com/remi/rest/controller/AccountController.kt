package com.remi.rest.controller

import com.remi.rest.data.AccountData
import com.remi.rest.response.Get
import com.remi.rest.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController {

    @Autowired
    private lateinit var service: AccountService

    @GetMapping("/account/{id}")
    suspend fun get(@PathVariable("id") id: String, @RequestParam(value="keyword") keyword: String?): Get<AccountData> {
        val resp: Get<AccountData> = try {
            val result = service.get(id, keyword)
            if(result != null) {
                Get<AccountData>(result)
            } else {
                Get("response null")
            }
        } catch (ex: Exception) {
            Get(ex.message.toString())
        }
        return resp;
    }
}