package ting.li.accountsecurity.models;

import lombok.Data;

@Data
public class TokenModel {
    private String accessToken;
    private String refreshToken;
}
