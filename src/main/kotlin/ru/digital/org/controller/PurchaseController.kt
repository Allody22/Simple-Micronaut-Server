package ru.digital.org.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import ru.digital.org.model.GasStationPurchase
import ru.digital.org.payload.request.NewPurchaseRequest
import ru.digital.org.service.GasStationService

@Controller("/purchase")
@Secured(SecurityRule.IS_AUTHENTICATED)
class PurchaseController (private val gasStationService: GasStationService) {

    @Post("/save")
    @Produces(MediaType.APPLICATION_JSON)
    fun savePurchase(@Body newPurchaseRequest: NewPurchaseRequest, authentication: Authentication): String {
        val username = authentication.name

        gasStationService.saveNewPurchase(newPurchaseRequest, username)

        return "Заказ успешно сохранён"
    }


    @Get("/get/page/{limit}/{offset}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserPurchasePageable(authentication: Authentication,@PathVariable limit: Int, @PathVariable offset:Int): List<GasStationPurchase> {
        val username = authentication.name

        val userPurchase = gasStationService.getUserPurchasePageable(limit, offset ,username);
        return userPurchase
    }

    @Get("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserPurchase(authentication: Authentication): List<GasStationPurchase> {
        val username = authentication.name
        return gasStationService.getUserPurchase(username)
    }
}