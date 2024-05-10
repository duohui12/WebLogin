package com.example.weblogin.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private static final String SECRET = "12345678901234567890123456789010";
    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MX0.VNgyGgAvt_254_l7JtVU4n1qcsAjuYxUWpSGe69X9Fs";
    private static final String INVALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MX0.VNgyGgAvt_254_l7JtVU4n1qcsAjuYxUWpSGe69X9Fx";


    private JwtUtil jwtUtil;

    @BeforeEach
    void setup(){
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    void encode() {
        String accessToken = jwtUtil.encode(1L);
        assertThat(accessToken).isEqualTo(VALID_TOKEN);
    }

    @Test
    void decodeWithValidToken(){
        Claims claims = jwtUtil.decode(VALID_TOKEN);
        assertThat(claims.get("id",Long.class)).isEqualTo(1L);
    }

    @Test
    void decodeWithInvalidToken(){
        assertThatThrownBy(() -> jwtUtil.decode(INVALID_TOKEN))
                .isInstanceOf(SignatureException.class);
    }
}
