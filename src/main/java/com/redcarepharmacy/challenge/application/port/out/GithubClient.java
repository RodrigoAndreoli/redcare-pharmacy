package com.redcarepharmacy.challenge.application.port.out;

import com.redcarepharmacy.challenge.domain.Repository;

import java.util.List;

public interface GithubClient {

    List<Repository> getRepositories(String dateFrom, String language, Integer size);

}
