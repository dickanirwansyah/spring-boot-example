package com.dicka.examplevm.controller;

import com.dicka.examplevm.entity.Influencer;
import com.dicka.examplevm.request.InfluencerRequest;
import com.dicka.examplevm.service.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/influencer")
public class InfluencerController {

    @Autowired
    private InfluencerService influencerService;

    @PostMapping(value = "/singup")
    public Influencer signupInfluencer(@RequestBody @Valid  InfluencerRequest ir){
        return influencerService.newInfluencer(ir);
    }

    @PostMapping(value = "/confirm")
    public Influencer confirmInfluencer(@RequestParam("token") String token,
                                        @RequestParam("email") String email){

        return influencerService.confirmInfluencer(token, email);
    }
}
