package com.dicka.examplevm.service;

import com.dicka.examplevm.entity.Influencer;
import com.dicka.examplevm.entity.VerifyToken;
import com.dicka.examplevm.exception.ResourceConflictException;
import com.dicka.examplevm.exception.ResourceNotFoundException;
import com.dicka.examplevm.repo.InfluencerRepo;
import com.dicka.examplevm.repo.MarketerRepo;
import com.dicka.examplevm.repo.VerifyTokenRepo;
import com.dicka.examplevm.request.InfluencerRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class InfluencerService {

    private final InfluencerRepo influencerRepo;
    private final VerifyTokenRepo verifyTokenRepo;

    @Autowired
    public InfluencerService(InfluencerRepo influencerRepo, VerifyTokenRepo verifyTokenRepo){
        this.influencerRepo = influencerRepo;
        this.verifyTokenRepo = verifyTokenRepo;
    }

    /** service singup **/
    public Influencer newInfluencer(InfluencerRequest ir){

        Influencer influencer;

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

        return influencer;
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
