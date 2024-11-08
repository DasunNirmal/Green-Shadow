package lk.ijse.greenshadow.entity;

import jakarta.persistence.*;
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "field_code",referencedColumnName = "field_code")
    private FieldEntity field;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "log_code",referencedColumnName = "log_code")
    private MonitoringLogEntity monitoringLog;
    private String field_name;
    private Point field_location;
}
