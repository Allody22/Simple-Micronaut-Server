package ru.digital.org.configuration.security

import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.config.SecurityConfigurationProperties
import io.micronaut.security.rules.SecurityRule
import io.micronaut.security.rules.SecurityRuleResult
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

@Singleton
@Requires(property = SecurityConfigurationProperties.PREFIX + ".enabled", notEquals = "false")
class GlobalSecurityRule : SecurityRule<HttpRequest<*>> {

    override fun check(request: HttpRequest<*>?, authentication: Authentication?): Publisher<SecurityRuleResult> {
        return Mono.just(SecurityRuleResult.ALLOWED)
    }
}
