package com.redcarepharmacy.challenge.application;

import com.redcarepharmacy.challenge.application.port.out.GithubClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.redcarepharmacy.challenge.mock.MockFactory.buildRepositoryList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetRepositoriesUseCaseTest {

    private final GithubClient githubClient = mock(GithubClient.class);
    private final GetRepositoriesUseCase useCase = new GetRepositoriesUseCase(githubClient);

    @Test
    @DisplayName("GIVEN no parameters " +
            "WHEN invoking useCase to getRepositories " +
            "THEN GitHubClient should be called and the response should be of default size 10.")
    void getRepositoryListOk() {
        var expected = buildRepositoryList(10);

        when(githubClient.getRepositories(null, null, null)).thenReturn(expected);

        var result = useCase.execute(null, null, null);
        assertEquals(expected, result);
        assertEquals(expected.size(), result.size());
        verify(githubClient, times(1)).getRepositories(null, null, null);
    }

}