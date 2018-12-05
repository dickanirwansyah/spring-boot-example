package com.dicka.examplevm.repo;

import com.dicka.examplevm.entity.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InfluencerRepo extends JpaRepository<Influencer, String> {

    /** find by email optional **/
    Optional<Influencer> findByEmail(String email);

    /** find by email
    Influencer findByEmail(String email);
     **/

    /** find by nick **/
    List<Influencer> findByEmailAndNick(String email, String nick);
}
