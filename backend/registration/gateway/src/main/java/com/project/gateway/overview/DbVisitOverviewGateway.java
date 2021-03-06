package com.project.gateway.overview;

import com.project.domain.gateway.overview.WorkingHoursOverview;
import com.project.domain.gateway.overview.VisitOverview;
import com.project.domain.gateway.overview.VisitOverviewGateway;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class DbVisitOverviewGateway implements VisitOverviewGateway {

  private final VisitOverviewRepository repository;

  public DbVisitOverviewGateway(VisitOverviewRepository visitOverviewRepository) {
    this.repository = visitOverviewRepository;
  }

  @Override
  public Optional<VisitOverview> findBy(UUID id) {
    return repository.findById(id);
  }

  @Override
  public Collection<WorkingHoursOverview> findByDoctorIdAndMonth(String doctorId, LocalDate date) {
    return repository.findByDoctorAndMonth(doctorId, date.getYear(), date.getMonthValue());
  }

  @Override
  public Collection<WorkingHoursOverview> findByDoctorIdAndWeek(String doctorId, LocalDate date) {
    return repository.findByDoctorAndWeek(doctorId, date.getYear(), date.getMonthValue(), date.getDayOfMonth());
  }
}
