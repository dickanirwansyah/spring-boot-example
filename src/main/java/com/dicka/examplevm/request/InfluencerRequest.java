package com.dicka.examplevm.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfluencerRequest {

    @NotBlank(message = "please enter the email.")
    @Email(message = "sorry email not valid.")
    private String requestEmail;

    @NotBlank(message = "please enter name.")
    private String requestName;

    @NotBlank(message = "please enter nick.")
    private String requestNick;

    @NotBlank(message = "please enter address.")
    private String requestAddress;

    @NotBlank(message = "please enter password.")
    private String requestPasswd;

}
