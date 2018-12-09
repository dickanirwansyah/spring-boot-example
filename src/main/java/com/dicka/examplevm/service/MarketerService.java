package com.dicka.examplevm.service;

import com.dicka.examplevm.entity.Marketer;
import com.dicka.examplevm.entity.VerifyToken;
import com.dicka.examplevm.exception.ResourceConflictException;
import com.dicka.examplevm.exception.ResourceNotFoundException;
import com.dicka.examplevm.repo.MarketerRepo;
import com.dicka.examplevm.repo.VerifyTokenRepo;
import com.dicka.examplevm.request.MarketerRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class MarketerService {

    private final MarketerRepo marketerRepo;
    private final VerifyTokenRepo verifyTokenRepo;
    /** check logger
    private static final Logger log = LoggerFactory
            .getLogger(MarketerService.class);
     **/

    @Autowired
    public MarketerService(MarketerRepo marketerRepo, VerifyTokenRepo verifyTokenRepo){
        this.marketerRepo = marketerRepo;
        this.verifyTokenRepo = verifyTokenRepo;
    }

    /** list markter **/
    public List<Marketer> listMarketer(){
        log.info("processing list..");
        List<Marketer> marketers = new ArrayList<>();
        for (Marketer marketer : marketerRepo.findAll()){
            marketers.add(marketer);
        }
        return marketers;
    }

    /** new marketer or signup**/
    public Marketer newMarketer(MarketerRequest rm){

        Marketer marketer;

        if (verifyTokenRepo.findByEmail(rm.getRequestEmail()).isPresent()){
            log.info("email sudah ada : "+rm.getRequestEmail()+" !");
            throw new ResourceConflictException("sorry email : "
                    +rm.getRequestEmail()+" is already exist.");
        }else {

            /** create marketer **/
            marketer = Marketer.builder()
                    .email(rm.getRequestEmail())
                    .name(rm.getRequestName())
                    .address(rm.getRequestAddress())
                    .passwd(rm.getRequestPasswd())
                    .nick(rm.getRequestNick())
                    .stVer(0)
                    .build();

            marketerRepo.save(marketer);

            /** create token from markter **/
            VerifyToken token = VerifyToken
                    .builder()
                    .confirmKey(UUID.randomUUID().toString())
                    .email(rm.getRequestEmail())
                    .confirmStatus("p")
                    .build();

            verifyTokenRepo.save(token);
        }

        return marketer;
    }
}
