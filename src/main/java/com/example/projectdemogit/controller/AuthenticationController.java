package com.example.projectdemogit.controller;

import com.example.projectdemogit.config.CustomUserDetails;
import com.example.projectdemogit.dtos.request.user.CreateUserDto;
import com.example.projectdemogit.dtos.request.user.LoginUserDto;
import com.example.projectdemogit.dtos.response.CustomResponse;
import com.example.projectdemogit.entity.User;
import com.example.projectdemogit.jwt.JwtTokenProvider;
import com.example.projectdemogit.service.UserService;
import com.example.projectdemogit.utils.AuthenticationUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login-jwt")
    public ResponseEntity<CustomResponse> authenticateUser(@RequestBody @Valid LoginUserDto dto ,BindingResult result) {
        // Xác thực thông tin đăng nhập
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        // Lấy thông tin người dùng từ đối tượng Authentication
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        // response JWT
        return ResponseEntity.ok(userService.validateUserAndGenerateToken(dto,result,userDetails));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse> admin() {
        return ResponseEntity.ok(new CustomResponse("Welcome come admin", HttpStatus.OK.value(), ""));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CustomResponse> user() {
        return ResponseEntity.ok(new CustomResponse("Welcome come USER", HttpStatus.OK.value(), ""));
    }

    @GetMapping("/warehouse")
    @PreAuthorize("hasAuthority('WAREHOUSE_MANAGER')")
    public ResponseEntity<CustomResponse> warehouse() {
        return ResponseEntity.ok(new CustomResponse("Welcome come WAREHOUSE_MANAGER", HttpStatus.OK.value(), ""));
    }

    @GetMapping("/seller")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<CustomResponse> seller() {
        return ResponseEntity.ok(new CustomResponse("Welcome come SELLER", HttpStatus.OK.value(), ""));
    }

    @GetMapping("/customer")
    @PreAuthorize("hasAuthority('CUSTOMER_VIP')")
    public ResponseEntity<CustomResponse> customer() {
        return ResponseEntity.ok(new CustomResponse("Welcome come CUSTOMER_VIP", HttpStatus.OK.value(), ""));
    }

    @GetMapping("/auth/403")
    public ResponseEntity<String> accessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
    }

    @GetMapping("/home")
    public ResponseEntity<CustomResponse> home(Authentication authentication) {
        /*get info Oauth2*/
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof DefaultOAuth2User) {
            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
            Collection<? extends GrantedAuthority> authorities = defaultOAuth2User.getAuthorities();
            String email = defaultOAuth2User.getAttribute("email");
            List<String> roles = authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse("Login with google Successfully", HttpStatus.OK.value(), "email : " + email + " - " + roles));
        }
        /*get info User*/
        if (authentication != null && authentication.isAuthenticated()) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            String username = authentication.getName(); // Lấy tên người dùng từ xác thực
            List<String> roles = AuthenticationUtils.getRoles(customUserDetails);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse("Login ok Successfully", HttpStatus.OK.value(), "username : " + username + " - " + roles));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CustomResponse("UNAUTHORIZED", HttpStatus.UNAUTHORIZED.value(), null));

    }
    @PostMapping("/auth/logout-jwt")
    public ResponseEntity<CustomResponse> logout() {
        return ResponseEntity.ok(new CustomResponse("Logout jwt ok", HttpStatus.OK.value(), ""));
    }
}
