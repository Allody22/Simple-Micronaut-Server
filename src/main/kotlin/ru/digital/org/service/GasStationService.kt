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