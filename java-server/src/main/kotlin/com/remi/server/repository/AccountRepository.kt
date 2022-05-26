package com.remi.server.repository

import com.remi.server.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository: JpaRepository<Account, String> {
    fun findByIdAndName(id: String, name: String): Optional<Account>
}