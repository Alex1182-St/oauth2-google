package com.example.oauth2google.entities



import java.util.*
import javax.persistence.*

@Entity
@Table(name = "googleUsers")
data class GoogleUserEntity (

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(updatable = false, nullable = false, unique = true)
        val id : UUID = UUID.randomUUID(),

        @Column(nullable = false)
        val email : String,

        @Column(nullable = false)
        val userType : UserTypeEnum,

        @Column(nullable = false)
        val name : String


        )