package lk.ijse.greenshadow.entity.impl;

import jakarta.persistence.*;
import lk.ijse.greenshadow.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "field_monitoring_details")
public class FieldMonitoringDetails implements SuperEntity {
    @Id
    private String details_id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "field_code",referencedColumnName = "field_code")
    private FieldEntity field;
    @ManyToOne
    @JoinColumn(name = "log_code",referencedColumnName = "log_code")
    private MonitoringLogEntity monitoringLog;
    private String field_name;
    private String field_location;
}
