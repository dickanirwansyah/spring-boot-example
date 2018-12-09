package com.dicka.examplevm.controller;

import com.dicka.examplevm.entity.Marketer;
import com.dicka.examplevm.request.MarketerRequest;
import com.dicka.examplevm.service.MarketerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/marketer")
public class MarketerController {

    @Autowired
    private MarketerService marketerService;

    /** singup marketer **/
    @PostMapping(value = "/singup")
    public Marketer singupMarketer(@RequestBody @Valid  MarketerRequest request){
        return marketerService.newMarketer(request);
    }
}
