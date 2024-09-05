package ru.digital.org.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import ru.digital.org.model.GasStationPurchase
import ru.digital.org.payload.request.NewPurchaseRequest
import ru.digital.org.payload.request.UserNameRequest
import ru.digital.org.service.GasStationService

@Controller("/purchase")
@Secured(SecurityRule.IS_AUTHENTICATED)
class PurchaseController (private val gasStationService: GasStationService) {

    @Post("/save")
    @Produces(MediaType.APPLICATION_JSON)
    fun savePurchase(@Body newPurchaseRequest: NewPurchaseRequest, authentication: Authentication): String {
        val username = authentication.name

        // Вы можете передать имя пользователя в ваш сервис
        gasStationService.saveNewPurchase(newPurchaseRequest, username)

        return "Заказ успешно сохранён"
    }


    @Post("/get")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserPurchase(@Body userNameRequest: UserNameRequest, authentication: Authentication): List<GasStationPurchase> {
        val username = authentication.name

        // Получаем покупки пользователя на основе его имени
        return gasStationService.getUserPurchase(username)
    }
}