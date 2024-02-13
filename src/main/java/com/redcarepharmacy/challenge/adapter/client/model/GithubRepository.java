package com.redcarepharmacy.challenge.adapter.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redcarepharmacy.challenge.domain.Repository;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GithubRepository {

    @JsonProperty("id")
    Integer id;
    @JsonProperty("name")
    String name;
    @JsonProperty("owner")
    RepositoryOwner owner;
    @JsonProperty("description")
    String description;
    @JsonProperty("html_url")
    String url;
    @JsonProperty("created_at")
    String createdAt;
    @JsonProperty("language")
    String language;
    @JsonProperty("stargazers_count")
    Integer stars;

    public Repository toDomain() {
        return Repository.builder()
                .id(id)
                .name(name)
                .owner(owner.getName())
                .description(description)
                .url(url)
                .createdAt(createdAt)
                .language(language)
                .stars(stars)
                .build();
    }

}