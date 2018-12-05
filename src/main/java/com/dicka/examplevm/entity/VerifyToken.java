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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VERIFY_TOKEN")
public class VerifyToken implements Serializable{

    /** confirm key is token user !**/
    @Id
    @Column(name = "CONFIRM_KEY")
    private String confirmKey;

    @Column(name = "CONFIRM_STATUS")
    private String confirmStatus;
    private String email;
}
