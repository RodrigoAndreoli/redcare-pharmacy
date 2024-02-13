package com.redcarepharmacy.challenge.adapter.controller;

import com.redcarepharmacy.challenge.adapter.controller.model.RepositoryResponse;
import com.redcarepharmacy.challenge.application.port.in.GetRepositoriesQuery;
import com.redcarepharmacy.challenge.domain.Repository;
import com.redcarepharmacy.challenge.util.SizeValidation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/repository")
public class RepositoryController {

    private final GetRepositoriesQuery getRepositoriesQuery;

    @GetMapping()
    public List<RepositoryResponse> getRepositories(
            @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) @Valid
            @SizeValidation() Integer size
    ) {
        log.info(">> Executing GetRepositories query with parameters: DateFrom [{}], Language [{}], Size [{}]",
                dateFrom, language, size);
        List<Repository> repositories = getRepositoriesQuery.execute(dateFrom, language, size);
        List<RepositoryResponse> response = repositories.stream()
                .map(RepositoryResponse::fromDomain)
                .collect(Collectors.toList());
        log.info("<< Request successfully executed with a response size of {}", response.size());
        return response;
    }

}
