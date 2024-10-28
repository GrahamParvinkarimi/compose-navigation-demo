package com.gp.sample_feature.data

import com.gp.sample_feature.domain.model.User
import java.util.UUID
import javax.inject.Inject

interface UserRepository {
    fun retrieveUsers(): List<User>
}

class UserRepositoryImpl @Inject constructor() : UserRepository {
    private val users = listOf(
        User(
            id = UUID.randomUUID().toString(),
            username = "dog_lover1234",
            email = "chris@aol.com",
            phoneNumber = "831-213-4124"
        ),
        User(
            id = UUID.randomUUID().toString(),
            username = "steve91",
            email = "steve@aol.com",
            phoneNumber = "901-213-4112"
        ),
        User(
            id = UUID.randomUUID().toString(),
            username = "hiking90",
            email = "richard@aol.com",
            phoneNumber = "312-412-4212"
        ),
        User(
            id = UUID.randomUUID().toString(),
            username = "home_design31",
            email = "luis@aol.com",
            phoneNumber = "703-122-2411"
        )
    )

    override fun retrieveUsers(): List<User> {
        return users
    }
}