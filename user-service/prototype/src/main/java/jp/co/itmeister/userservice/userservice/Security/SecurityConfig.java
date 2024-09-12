package jp.co.itmeister.userservice.userservice.Security; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @SuppressWarnings({ "removal", "deprecation" })
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // CSRFを無効化（必要に応じて有効にできます）
            .authorizeRequests()
            .anyRequest().permitAll()  // 全てのリクエストを認証なしで許可
            .and()
            .formLogin().disable();  // デフォルトのログインフォームを無効化

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // パスワードハッシュ用のエンコーダー
    }
}