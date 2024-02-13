package com.redcarepharmacy.challenge.mock;

import com.redcarepharmacy.challenge.adapter.client.model.GithubRepositoriesSearchResponse;
import com.redcarepharmacy.challenge.adapter.client.model.GithubRepository;
import com.redcarepharmacy.challenge.adapter.client.model.RepositoryOwner;
import com.redcarepharmacy.challenge.domain.Repository;

import java.util.ArrayList;
import java.util.List;

public class MockFactory {

    /*### REPOSITORY STUBS ###*/
    private static final Integer REPOSITORY_ID = 123456789;
    private static final String REPOSITORY_NAME = "someRepository";
    private static final String REPOSITORY_DESCRIPTION = "someDescription";
    private static final String REPOSITORY_URL = "someUrl";
    private static final String REPOSITORY_CREATED_AT = "someCreationDate";
    private static final String REPOSITORY_LANGUAGE = "someLanguage";
    private static final Integer REPOSITORY_STARS = 150;

    /*### OWNER STUBS ###*/
    private static final Integer OWNER_ID = 1122334455;
    private static final String OWNER_NAME = "someOwner";
    private static final String OWNER_URL = "someOwnerUrl";

    public static GithubRepositoriesSearchResponse buildSearchResponse(Integer size) {
        return GithubRepositoriesSearchResponse.builder()
                .totalCount(size)
                .incompleteResults(false)
                .items(buildGithubRepositoryList(size))
                .build();
    }

    public static List<GithubRepository> buildGithubRepositoryList(Integer size) {
        List<GithubRepository> repositories = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            repositories.add(buildGithubRepository());
        }
        return repositories;
    }

    public static GithubRepository buildGithubRepository() {
        return GithubRepository.builder().id(REPOSITORY_ID)
                .name(REPOSITORY_NAME)
                .owner(RepositoryOwner.builder()
                        .id(OWNER_ID)
                        .name(OWNER_NAME)
                        .url(OWNER_URL)
                        .build())
                .description(REPOSITORY_DESCRIPTION)
                .url(REPOSITORY_URL)
                .createdAt(REPOSITORY_CREATED_AT)
                .language(REPOSITORY_LANGUAGE)
                .stars(REPOSITORY_STARS)
                .build();
    }

    public static List<Repository> buildRepositoryList(Integer size) {
        List<Repository> repositories = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            repositories.add(buildRepository());
        }
        return repositories;
    }

    public static Repository buildRepository() {
        return Repository.builder()
                .id(REPOSITORY_ID)
                .name(REPOSITORY_NAME)
                .owner(OWNER_NAME)
                .description(REPOSITORY_DESCRIPTION)
                .url(REPOSITORY_URL)
                .createdAt(REPOSITORY_CREATED_AT)
                .language(REPOSITORY_LANGUAGE)
                .stars(REPOSITORY_STARS)
                .build();
    }

}
