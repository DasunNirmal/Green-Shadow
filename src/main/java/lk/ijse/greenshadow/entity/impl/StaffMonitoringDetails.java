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
@Table(name = "staff_monitoring_details")
public class StaffMonitoringDetails implements SuperEntity {
    @Id
    private String detail_id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "staff_id",referencedColumnName = "staff_id")
    private StaffEntity staff;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "log_code",referencedColumnName = "log_code")
    private MonitoringLogEntity monitoringLog;
    private String first_name;
    private String phone_no;
    private String details;
}
