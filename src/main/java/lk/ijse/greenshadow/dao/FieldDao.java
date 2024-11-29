package lk.ijse.greenshadow.dao;

import lk.ijse.greenshadow.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldDao extends JpaRepository<FieldEntity,String> {
}
