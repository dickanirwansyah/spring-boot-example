package com.dicka.examplevm.controller;

import com.dicka.examplevm.entity.Influencer;
import com.dicka.examplevm.request.InfluencerLoginRequest;
import com.dicka.examplevm.request.InfluencerRequest;
import com.dicka.examplevm.service.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping(value = "/api/influencer")
public class InfluencerController {

    @Autowired
    private InfluencerService influencerService;

    @GetMapping(value = "/maps")
    public Map<String, List> getMapInfluencer(@RequestParam("email") String email){
        return influencerService.findByEmails(email);
    }

    @GetMapping(value = "/params")
    public List<Influencer> getParamsEmail(@RequestParam("email") String email){
        return influencerService.findInfluencerByParamsEmail(email);
    }

    @PostMapping(value = "/singup")
    public ResponseEntity<Object> signupInfluencer(@RequestBody @Valid  InfluencerRequest ir,
                                                   BindingResult bindingResult){
        return influencerService.newInfluencer(ir, bindingResult);
    }

    @PostMapping(value = "/confirm")
    public Influencer confirmInfluencer(@RequestParam("token") String token,
                                        @RequestParam("email") String email){

        return influencerService.confirmInfluencer(token, email);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> loginInfluencer(@RequestBody @Valid InfluencerLoginRequest login,
                                                  BindingResult bindingResult){

        return influencerService.loginInfluencer(login, bindingResult);
    }
}
