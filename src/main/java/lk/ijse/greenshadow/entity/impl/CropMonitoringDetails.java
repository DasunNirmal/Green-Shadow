package lk.ijse.greenshadow.entity.impl;

import jakarta.persistence.*;
import lk.ijse.greenshadow.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "crop_monitoring_details")
public class CropMonitoringDetails implements SuperEntity {
    @Id
    private String details_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crop_code",referencedColumnName = "crop_code")
    private CropEntity crops;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "log_code",referencedColumnName = "log_code")
    private MonitoringLogEntity monitoringLog;
    private String details;
    private String crop_name;
}
