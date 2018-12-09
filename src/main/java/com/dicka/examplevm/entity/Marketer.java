package com.dicka.examplevm.entity;

import lombok.*;

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
@Table(name = "MARKETER")
public class Marketer implements Serializable{

    @Id
    private String email;
    private String name;
    private String nick;
    private String address;
    private String passwd;

    @Column(name = "ST_VER")
    private int stVer;
}
