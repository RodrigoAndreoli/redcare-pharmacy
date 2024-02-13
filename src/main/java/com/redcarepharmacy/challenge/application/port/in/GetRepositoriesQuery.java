package com.redcarepharmacy.challenge.application.port.in;

import com.redcarepharmacy.challenge.domain.Repository;

import java.util.List;

public interface GetRepositoriesQuery {

    List<Repository> execute(String dateFrom, String language, Integer size);

}
