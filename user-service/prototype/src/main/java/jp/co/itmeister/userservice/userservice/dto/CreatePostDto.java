package jp.co.itmeister.userservice.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CreatePostDto extends PostDto {
    @JsonIgnore
    @Override
    public void setUrl(String url) {

    }

    @JsonIgnore
    @Override
    public String getUrl () {
        return null;
    }
}
