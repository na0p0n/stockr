package net.naoponju.stockr.infra.auth

import net.naoponju.stockr.domain.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class StockrUserDetails(
    private val user: User
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> =
        listOf(SimpleGrantedAuthority("ROLE_${user.roleId}"))

    override fun getPassword(): String = user.passwordHash
    override fun getUsername(): String = user.username
    override fun isEnabled(): Boolean = user.isActive
}