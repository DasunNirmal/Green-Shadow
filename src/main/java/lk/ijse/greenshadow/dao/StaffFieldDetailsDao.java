package lk.ijse.greenshadow.dao;

import lk.ijse.greenshadow.entity.impl.FieldStaffDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffFieldDetailsDao extends JpaRepository<FieldStaffDetailsEntity,String> {
}
