package net.naoponju.stockr.infra.auth

import net.naoponju.stockr.infra.mapper.UserMapper
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class StockrUserDetailsService(
    private val userMapper: UserMapper,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user =
            userMapper.selectByUsername(username)
                ?: throw UsernameNotFoundException("ユーザーが見つかりません")
        return StockrUserDetails(user)
    }
}
