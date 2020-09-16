package com.example.oauth2google.repositories

import com.example.oauth2google.entities.GoogleUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GoogleUserRepository : JpaRepository<GoogleUserEntity, String>{

    fun findByEmail(email : String) : Optional<GoogleUserEntity>

}