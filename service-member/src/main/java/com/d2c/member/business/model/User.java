package com.d2c.member.business.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class User implements Serializable {

    Long id;
    @NonNull
    String username;
    @NonNull
    String password;

}
