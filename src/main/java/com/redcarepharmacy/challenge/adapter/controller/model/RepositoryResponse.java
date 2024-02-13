package com.redcarepharmacy.challenge.adapter.controller.model;

import com.redcarepharmacy.challenge.domain.Repository;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RepositoryResponse {

    Integer id;
    String name;
    String owner;
    String description;
    String url;
    String createdAt;
    String language;
    Integer stars;

    public static RepositoryResponse fromDomain(Repository repository) {
        return RepositoryResponse.builder()
                .id(repository.getId())
                .name(repository.getName())
                .owner(repository.getOwner())
                .description(repository.getDescription())
                .url(repository.getUrl())
                .createdAt(repository.getCreatedAt())
                .language(repository.getLanguage())
                .stars(repository.getStars())
                .build();
    }

}
