package com.d2c.member.mongodb.document;

import com.d2c.member.business.model.User;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class UserMongo {

    @Id
    Long id;

    @NonNull
    String username;

    @NonNull
    String password;

    public UserMongo() {
    }

    public UserMongo(User user) {
        this();
        BeanUtils.copyProperties(user, this);
    }

}
