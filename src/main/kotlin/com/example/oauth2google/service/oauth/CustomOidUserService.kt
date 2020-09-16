package com.example.oauth2google.service.oauth

import com.example.oauth2google.dto.GoogleOauth2UserInfo
import com.example.oauth2google.entities.GoogleUserEntity
import com.example.oauth2google.entities.UserTypeEnum
import com.example.oauth2google.repositories.GoogleUserRepository
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Service



@Service
class CustomOidUserService(private val googleUserRepo : GoogleUserRepository)
    : OidcUserService() {

    override fun loadUser(userRequest: OidcUserRequest): OidcUser {

        val oidUser: OidcUser = super.loadUser(userRequest)
        val attributes: Map<String, Any> = oidUser.attributes
        val userInfo = GoogleOauth2UserInfo(
                id = attributes["sub"] as String,
                name = attributes["name"] as String,
                email = attributes["email"] as String
        )
        updateUser(userInfo)
        return oidUser
    }

    fun updateUser(userInfo : GoogleOauth2UserInfo) {

        val googleUserEntity : GoogleUserEntity = googleUserRepo
                .findByEmail(userInfo.email)
                .orElse(
                        GoogleUserEntity(
                                name = userInfo.name,
                                userType = UserTypeEnum.GOOGLE,
                                email = userInfo.email
                                ))

        googleUserRepo.save(googleUserEntity)

        }

    }

