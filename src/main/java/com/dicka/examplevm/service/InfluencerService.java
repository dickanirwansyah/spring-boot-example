package com.dicka.examplevm.service;

import com.dicka.examplevm.entity.Influencer;
import com.dicka.examplevm.entity.VerifyToken;
import com.dicka.examplevm.exception.ResourceConflictException;
import com.dicka.examplevm.exception.ResourceNotFoundException;
import com.dicka.examplevm.repo.InfluencerRepo;
import com.dicka.examplevm.repo.VerifyTokenRepo;
import com.dicka.examplevm.request.InfluencerLoginRequest;
import com.dicka.examplevm.request.InfluencerRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

@Slf4j
@Service
public class InfluencerService {

    private final InfluencerRepo influencerRepo;
    private final VerifyTokenRepo verifyTokenRepo;
    Map<String, String> errors;

    @Autowired
    public InfluencerService(InfluencerRepo influencerRepo, VerifyTokenRepo verifyTokenRepo){
        this.influencerRepo = influencerRepo;
        this.verifyTokenRepo = verifyTokenRepo;
    }

    /** service login **/
    public ResponseEntity<Object> loginInfluencer(InfluencerLoginRequest requestLogin,
                                                  BindingResult bindingResult){

        /** validation if field null **/
        if (bindingResult.hasErrors()){
            errors = new HashMap<>();
            for (FieldError fieldError: bindingResult.getFieldErrors()){
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Object>(errors, HttpStatus.NOT_ACCEPTABLE);
        }

        /** check influencer **/
        Optional<Influencer> loginInfluencer = influencerRepo
                .findByEmailAndPasswd(requestLogin.getEmail(), requestLogin.getPassword());

        if (!loginInfluencer.isPresent()){
            throw new ResourceNotFoundException("sorry email and password failed to login.");
        }

        return new ResponseEntity<Object>(loginInfluencer, HttpStatus.OK);
    }

    /** service singup **/
    public ResponseEntity<Object> newInfluencer(InfluencerRequest ir,
                                    BindingResult bindingResult){

        Influencer influencer;

        if (bindingResult.hasErrors()){
            errors = new HashMap<>();
            for (FieldError fieldError: bindingResult.getFieldErrors()){
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Object>(errors, HttpStatus.NOT_ACCEPTABLE);
        }

        if(influencerRepo.findByEmail(ir.getRequestEmail()).isPresent()){
            throw new ResourceConflictException("sorry email : "+ir.getRequestEmail()+"" +
                    " is already exist.");
        }else{
            /** insert influencer **/
            influencer = Influencer
                    .builder()
                    .email(ir.getRequestEmail())
                    .name(ir.getRequestName())
                    .nick(ir.getRequestNick())
                    .address(ir.getRequestNick())
                    .passwd(ir.getRequestPasswd())
                    .stVer(0)
                    .build();

            influencerRepo.save(influencer);

            /** insert verify_token **/
            VerifyToken token = VerifyToken
                    .builder()
                    .confirmKey(UUID.randomUUID().toString())
                    .email(influencer.getEmail())
                    /** if confirm status ok v **/
                    .confirmStatus("p")
                    .build();

            verifyTokenRepo.save(token);
        }

        return new ResponseEntity<Object>(influencer, HttpStatus.CREATED);
    }

    public Map<String, List> findByEmails(String email){
        Map<String, List> map = new HashMap<>();
        List<Influencer> influencers = new ArrayList<>();
        for (Influencer influencer : influencerRepo.findInfluencerByEmail(email)){
            influencers.add(influencer);
            map.put("data", influencers);
        }
        return map;
    }

    public List<Influencer> findInfluencerByParamsEmail(String email){
        return influencerRepo.findInfluencerByEmail(email);

    }

    /** service confirmed singup **/
    public Influencer confirmInfluencer(String verifyToken, String email){
        /**verify token**/
        verifyTokens(verifyToken, email);
        return influencerRepo.findById(email)
                .map(influencer -> {
                    influencer.setStVer(1);
                    return influencerRepo.save(influencer);
                }).orElseThrow(() -> new ResourceNotFoundException("email : "
                        +email+" not found"));
    }

    private void verifyTokens(String verifyToken, String email){
        Optional<VerifyToken> findToken = verifyTokenRepo.findById(verifyToken);
        Optional<VerifyToken> findEmail = verifyTokenRepo.findByEmail(email);

        if (findToken == null || findEmail == null){
            Logger logger = LoggerFactory.getLogger("failed");
            throw new ResourceNotFoundException("sorry email and token not found.");
        }else {
            Logger logger = LoggerFactory.getLogger("proses update confirm status berhasil.");
            verifyTokenRepo.findById(verifyToken)
                    .map(token -> {
                        token.setConfirmStatus("v");
                        return verifyTokenRepo.save(token);
                    }).orElseThrow(() ->
                    new ResourceNotFoundException("token : "+verifyToken+" not found."));
        }
    }
}
