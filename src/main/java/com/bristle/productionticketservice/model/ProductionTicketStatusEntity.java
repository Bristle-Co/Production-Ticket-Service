package com.bristle.productionticketservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "production_ticket_status")
public class ProductionTicketStatusEntity {

    public static final String TABLE_NAME =  "production_ticket_status";

    public static final String STATUS_ID = "status_id";
    public static final String STATUS_NAME = "status_name";

    @Id
    @Column(name = STATUS_ID, nullable = false)
    private int statusId;

    @Column(name = STATUS_NAME, nullable = false)
    private String statusName;

    public ProductionTicketStatusEntity() {}

    public ProductionTicketStatusEntity(int statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
