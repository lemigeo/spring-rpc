package com.remi.server.entity

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "account")
class Account(
    @Id
    var id: String? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "created_at")
    var createdAt: ZonedDateTime? = ZonedDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: ZonedDateTime? = ZonedDateTime.now(),

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "type")
    var type: String? = null
)