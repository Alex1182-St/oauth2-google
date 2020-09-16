package com.example.oauth2google.controllers

import com.example.oauth2google.entities.GoogleUserEntity
import com.example.oauth2google.repositories.GoogleUserRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("app")
class GoogleUserController  (val googleUserRepo : GoogleUserRepository) {

    @PostMapping("byMail")
    fun appUserByMailWithPost(@RequestBody bodyWithEmail: Map<String, Any>): GoogleUserEntity {

        return googleUserRepo.findByEmail(bodyWithEmail.get("googleUserEmail") as String)

                .orElseThrow {
                    Exception("User with such email is not present in the database")
                }
    }}
