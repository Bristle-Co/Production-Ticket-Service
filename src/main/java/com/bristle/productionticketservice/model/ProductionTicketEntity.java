package com.bristle.productionticketservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "production_tickets")
public class ProductionTicketEntity {

    public static final String TABLE_NAME= "production_tickets";

    public static final String COLM_TICKET_ID= "ticket_id";

    @Id
    @Column(name = "")
    private Long ticketId;

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }


    public Long getTicketId() {
        return ticketId;
    }
}
