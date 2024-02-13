package com.redcarepharmacy.challenge.adapter.client;

import com.redcarepharmacy.challenge.adapter.client.feign.GithubClientFeign;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static com.redcarepharmacy.challenge.mock.MockFactory.buildSearchResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GithubClientAdapterTest {

    private final GithubClientFeign githubClientFeign = mock(GithubClientFeign.class);
    private final GithubClientAdapter adapter = new GithubClientAdapter(githubClientFeign);

    @Test
    @DisplayName("GIVEN no parameters " +
            "WHEN invoking clientAdapter to getRepositories " +
            "THEN GitHubFeignClient should be called and the response should be of default size 10.")
    void getRepositoryListOk() {
        var expected = buildSearchResponse(10);

        when(githubClientFeign.searchRepositories("", "stars", "desc", 10)).thenReturn(expected);

        var result = adapter.getRepositories(null, null, null);

        assertEquals(expected.getItems().size(), result.size());
        verify(githubClientFeign, times(1))
                .searchRepositories("", "stars", "desc", 10);
    }

    @Test
    @DisplayName("GIVEN language java as parameter and size 50 " +
            "WHEN invoking clientAdapter to getRepositories " +
            "THEN GitHubFeignClient should be called and the response should contain the correct language and size.")
    void getJavaRepositoryListOk() {
        var language = "java";
        var query = " language:" + language;
        var size = 50;
        var expected = buildSearchResponse(size);
        expected.setItems(
                expected.getItems().stream()
                        .peek(repository -> repository.setLanguage(language))
                        .collect(Collectors.toList())
        );

        when(githubClientFeign.searchRepositories(query, "stars", "desc", size)).thenReturn(expected);

        var result = adapter.getRepositories(null, language, size);

        assertEquals(expected.getItems().size(), result.size());
        assertEquals(language, result.get(0).getLanguage());
        verify(githubClientFeign, times(1))
                .searchRepositories(query, "stars", "desc", size);
    }

}