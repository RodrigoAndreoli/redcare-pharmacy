package com.redcarepharmacy.challenge.adapter.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GithubRepositoriesSearchResponse {

    @JsonProperty("total_count")
    Integer totalCount;

    @JsonProperty("incomplete_results")
    Boolean incompleteResults;

    @JsonProperty("items")
    List<GithubRepository> items;

}
