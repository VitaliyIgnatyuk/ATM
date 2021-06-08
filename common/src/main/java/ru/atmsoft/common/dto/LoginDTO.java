package ru.atmsoft.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private String deviceId;
    @Pattern(regexp = "^[0-9]{16}$")
    private String cardNum;
    @Pattern(regexp = "^[0-9]{4}$")
    private String pin;

}
