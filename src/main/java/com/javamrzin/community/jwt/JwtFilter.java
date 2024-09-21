package com.javamrzin.community.jwt;

import com.javamrzin.community.dto.CustomUserDetails;
import com.javamrzin.community.entity.Role;
import com.javamrzin.community.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("Token is not acceptable.");
            filterChain.doFilter(request, response);
            return; // 조건 만족 시 리턴 필수. (메서드 종료.)
        }

        // Bearer 제거 후 토큰만 획득.
        String token = authorization.split(" ")[1];

        // 토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)) {
            System.out.println("Token is Expired.");
            filterChain.doFilter(request, response);
            return; // 조건 만족 시 리턴 필수. (메서드 종료.)
        }

        // 토큰에서 username, role 획득
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        // 세션 저장용 회원 데이터 구성
        User user = new User();
        user.setUsername(username);
        user.setPassword("TempPassword");

        Role roleNew = new Role();
        roleNew.setName(role);
        user.getRoles().add(roleNew);

        // 세션 처리 및 다음 필터 진행.
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

}
