package com.dicka.examplevm.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InfluencerLoginRequest {

    @Email(message = "sorry email not valid")
    @NotBlank(message = "please enter email.")
    private String email;

    @NotBlank(message = "please enter password.")
    private String password;
}
