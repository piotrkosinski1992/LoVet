package com.project.usecase.overview;

import static com.project.commons.errors.ErrorCode.E_VISIT_01;

import com.project.domain.gateway.overview.FindVisitOverview;
import com.project.domain.gateway.overview.VisitOverview;
import com.project.domain.gateway.overview.VisitOverviewGateway;
import com.project.usecase.overview.exception.VisitNotFound;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class FindVisitOverviewUsecase implements FindVisitOverview {

  private final VisitOverviewGateway visitOverviewGateway;

  public FindVisitOverviewUsecase(VisitOverviewGateway visitOverviewGateway) {
    this.visitOverviewGateway = visitOverviewGateway;
  }

  @Override
  public VisitOverview findBy(UUID id) {
    return visitOverviewGateway.findBy(id)
        .orElseThrow(() -> new VisitNotFound(E_VISIT_01, id.toString()));
  }
}
