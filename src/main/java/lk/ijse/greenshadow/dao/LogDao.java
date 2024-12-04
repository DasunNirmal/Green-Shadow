package lk.ijse.greenshadow.dao;

import lk.ijse.greenshadow.entity.impl.MonitoringLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao extends JpaRepository<MonitoringLogEntity, String> {
}
