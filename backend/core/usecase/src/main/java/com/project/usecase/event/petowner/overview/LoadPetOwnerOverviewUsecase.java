package com.project.usecase.event.petowner.overview;

import com.project.domain.petowner.PetOwnerId;
import com.project.domain.petowner.gateway.overview.LoadPetOwnerOverview;
import com.project.domain.petowner.gateway.overview.PetOwnerOverview;
import com.project.domain.petowner.gateway.overview.PetOwnerOverviewGateway;
import com.project.usecase.event.petowner.exception.PetOwnerNotFound;
import org.springframework.stereotype.Service;

@Service
public class LoadPetOwnerOverviewUsecase implements LoadPetOwnerOverview {

  private final PetOwnerOverviewGateway gateway;

  public LoadPetOwnerOverviewUsecase(PetOwnerOverviewGateway gateway) {
    this.gateway = gateway;
  }


  @Override
  public PetOwnerOverview findBy(PetOwnerId id) {
    return gateway.findBy(id.value())
        .orElseThrow(() -> new PetOwnerNotFound(id.value()));
  }

}
