package com.redcarepharmacy.challenge.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Repository {

    Integer id;
    String name;
    String owner;
    String description;
    String url;
    String createdAt;
    String language;
    Integer stars;

}
