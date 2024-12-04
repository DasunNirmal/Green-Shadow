package lk.ijse.greenshadow.entity.impl;

import jakarta.persistence.*;
import lk.ijse.greenshadow.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "monitoring_log")
public class MonitoringLogEntity implements SuperEntity {
    @Id
    private String log_code;
    @Column(columnDefinition = "LONGTEXT")
    private String img;
    private String details;
    private String log_date;
    @OneToMany(mappedBy = "monitoringLog")
    private List<StaffMonitoringDetails> staffMonitoringDetails;
    @OneToMany(mappedBy = "monitoringLog")
    private List<FieldMonitoringDetails> fieldMonitoringDetails;
    @OneToMany(mappedBy = "monitoringLog")
    private List<CropMonitoringDetails> cropMonitoringDetails;
}
