package com.project.gateway.petowner;

import com.project.domain.petowner.PetOwner;
import com.project.domain.petowner.PetOwnerId;
import com.project.domain.petowner.PetOwnerSnapshot;
import com.project.domain.petowner.gateway.PetOwnerGateway;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DbPetOwnerGateway implements PetOwnerGateway {

  private final PetOwnerSnapshotRepository repository;

  public DbPetOwnerGateway(PetOwnerSnapshotRepository repository) {
    this.repository = repository;
  }

  @Override
  public void register(PetOwnerSnapshot snapshot) {
    repository.save(snapshot);
  }

  @Override
  public void save(PetOwnerSnapshot snapshot) {
    repository.save(snapshot);
  }

  @Override
  public Optional<PetOwner> findBy(PetOwnerId id) {
    return repository.findById(id.value()).map(PetOwner::fromSnapshot);
  }
}
