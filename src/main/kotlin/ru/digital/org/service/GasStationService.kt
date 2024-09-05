package ru.digital.org.service

import jakarta.inject.Singleton
import ru.digital.org.model.GasStationPurchase
import ru.digital.org.model.constants.ECategory
import ru.digital.org.model.exception.UserNotFoundException
import ru.digital.org.payload.request.NewPurchaseRequest
import ru.digital.org.repository.CategoryRepository
import ru.digital.org.repository.GasStationPurchaseRepository
import ru.digital.org.repository.UserRepository

@Singleton
class GasStationService(private val gasStationPurchaseRepository: GasStationPurchaseRepository,
    private val userRepository: UserRepository, private val categoryRepository: CategoryRepository
) {

    fun saveNewPurchase(newPurchaseRequest: NewPurchaseRequest, userName: String){
        val eCategory = ECategory.fromString(newPurchaseRequest.category)
        val user = userRepository.findByUsername(userName)

        if (!user.isPresent){
            throw UserNotFoundException(userName)
        }
        val purchase = GasStationPurchase(null, newPurchaseRequest.productName, newPurchaseRequest.price,
            eCategory, newPurchaseRequest.date, user.get().id)

        gasStationPurchaseRepository.save(purchase)
    }


    fun getUserPurchasePageable(limit: Int, offset: Int, userName: String) : List<GasStationPurchase> {
        val user = userRepository.findByUsername(userName)

        if (!user.isPresent){
            throw UserNotFoundException(userName)
        }

        return gasStationPurchaseRepository.getByUserPageable(user.get().id, limit, offset)
    }

    fun getUserPurchase(userName: String) : List<GasStationPurchase> {
        val user = userRepository.findByUsername(userName)

        if (!user.isPresent){
            throw UserNotFoundException(userName)
        }

        return gasStationPurchaseRepository.getByUser(user.get().id)
    }
}