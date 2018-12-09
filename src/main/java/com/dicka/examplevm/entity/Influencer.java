package com.dicka.examplevm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INFLUENCER")
public class Influencer implements Serializable{

    @Id
    private String email;
    private String name;
    private String nick;
    private String address;
    private String passwd;

    /** by default 0 **/
    /** by update 1 **/
    @Column(name = "ST_VER")
    private int stVer;

    /** many to many with spec result junction table INF_SPEC **/
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "INF_SPEC", joinColumns =
            {@JoinColumn(name = "INF_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SPEC_ID")})
    private Set<Spec> specs = new HashSet<>();
}
