package net.naoponju.stockr.present.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CookieCsrfTokenRepository

@Configuration
@EnableWebSecurity
@Profile("prod") // 本番プロファイルでのみ有効
class SecurityConfigProd {
    // 本番時に必要な厳格なセキュリティ設定
    @Bean
    fun securityFilterChainProd(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { // 本番ではCSRFを有効にする
                csrfTokenRepository = CookieCsrfTokenRepository()
            }
            authorizeHttpRequests {
                authorize("/login", permitAll)
                authorize("/css/**", permitAll)
                authorize(anyRequest, authenticated) // 認証済みユーザーのみ許可
            }
            formLogin {
                loginPage = "/login"
                permitAll()
            }
            logout {
                logoutUrl = "/logout"
                logoutSuccessUrl = "/login?logout"
                permitAll()
            }
            // ... その他の本番向け設定（フォームログイン、ログアウトなど）
        }
        return http.build()
    }
}
