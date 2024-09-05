/*
 * Copyright 2017-2024 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.digital.org.service

import jakarta.inject.Singleton
import ru.digital.org.model.GasStationPurchase
import ru.digital.org.model.constants.ECategory
import ru.digital.org.payload.request.NewPurchaseRequest
import ru.digital.org.repository.GasStationPurchaseRepository
import ru.digital.org.repository.UserRepository

@Singleton
class GasStationService(private val gasStationPurchaseRepository: GasStationPurchaseRepository,
    private val userRepository: UserRepository
) {

    fun saveNewPurchase(newPurchaseRequest: NewPurchaseRequest){
        val eCategory = ECategory.fromString(newPurchaseRequest.category)
        val user = userRepository.findByUsername(newPurchaseRequest.userName)

        if (!user.isPresent){
            //Какой-то свой эксепшен
            throw IllegalArgumentException("Username ${newPurchaseRequest.userName} not found")
        }
        val purchase = GasStationPurchase(null, newPurchaseRequest.productName, newPurchaseRequest.price,
            eCategory, newPurchaseRequest.date, user.get().id)

        gasStationPurchaseRepository.save(purchase)
    }


    fun getUserPurchase(userName: String) : List<GasStationPurchase> {
        val user = userRepository.findByUsername(userName)

        if (!user.isPresent){
            //Какой-то свой эксепшен
            throw IllegalArgumentException("Username $userName not found")
        }

        return gasStationPurchaseRepository.getByUser(user.get().id)
    }
}