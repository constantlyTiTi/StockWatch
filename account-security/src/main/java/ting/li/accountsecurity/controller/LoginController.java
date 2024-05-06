package ting.li.accountsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ting.li.accountsecurity.dto.AccountDto;

@RestController
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<AccountDto> login(@RequestBody AccountDto account){
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
