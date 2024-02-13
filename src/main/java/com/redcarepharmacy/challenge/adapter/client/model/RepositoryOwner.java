package com.redcarepharmacy.challenge.adapter.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RepositoryOwner {

    @JsonProperty("id")
    Integer id;
    @JsonProperty("login")
    String name;
    @JsonProperty("url")
    String url;

}
