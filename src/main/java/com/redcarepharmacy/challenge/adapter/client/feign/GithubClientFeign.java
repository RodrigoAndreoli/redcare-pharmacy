
package com.redcarepharmacy.challenge.adapter.client.feign;

import com.redcarepharmacy.challenge.adapter.client.model.GithubRepositoriesSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "github-client", url = "https://api.github.com")
public interface GithubClientFeign {

    @GetMapping(value = "/search/repositories")
    GithubRepositoriesSearchResponse searchRepositories(
            @RequestParam(value = "q") String query,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "order") String order,
            @RequestParam(value = "per_page") Integer size
    );

}
