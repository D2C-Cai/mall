package com.d2c.member.elasticsearch.document;

import com.d2c.member.business.model.User;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Document(indexName = "d2cmall-index", type = "user")
public class UserSearch implements Serializable {

    @Id
    Long id;
    @NonNull
    @Field(type = FieldType.Keyword)
    String username;
    @NonNull
    String password;

    public UserSearch() {
    }

    public UserSearch(User user) {
        this();
        BeanUtils.copyProperties(user, this);
    }

}
