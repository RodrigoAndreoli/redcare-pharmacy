package com.redcarepharmacy.challenge.adapter.client;

import com.redcarepharmacy.challenge.adapter.client.feign.GithubClientFeign;
import com.redcarepharmacy.challenge.adapter.client.model.GithubRepository;
import com.redcarepharmacy.challenge.application.port.out.GithubClient;
import com.redcarepharmacy.challenge.domain.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
public class GithubClientAdapter implements GithubClient {

    private final GithubClientFeign githubClientFeign;

    @Override
    public List<Repository> getRepositories(String dateFrom, String language, Integer size) {
        String query = "";
        if (hasText(language)) query = query.concat(" language:" + language);
        if (hasText(dateFrom)) query = query.concat(" created:>=" + dateFrom);
        if (size == null) size = 10;
        return githubClientFeign.searchRepositories(query, "stars", "desc", size)
                .getItems()
                .stream()
                .map(GithubRepository::toDomain)
                .collect(Collectors.toList());
    }

}
