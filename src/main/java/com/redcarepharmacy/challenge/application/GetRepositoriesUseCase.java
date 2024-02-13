package com.redcarepharmacy.challenge.application;

import com.redcarepharmacy.challenge.application.port.in.GetRepositoriesQuery;
import com.redcarepharmacy.challenge.application.port.out.GithubClient;
import com.redcarepharmacy.challenge.domain.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetRepositoriesUseCase implements GetRepositoriesQuery {

    private final GithubClient githubClient;

    @Override
    public List<Repository> execute(String dateFrom, String language, Integer size) {
        return githubClient.getRepositories(dateFrom, language, size);
    }

}
