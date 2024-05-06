package ting.li.accountsecurity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import lombok.*;
import ting.li.accountsecurity.enums.AccountStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    @JsonProperty("username")
    private String userName;
    @JsonProperty("password")
    private String password;
    @JsonProperty("account_status")
    @Nullable
    private AccountStatus accountStatus;

}
