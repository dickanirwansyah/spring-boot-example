package com.dicka.examplevm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
}
