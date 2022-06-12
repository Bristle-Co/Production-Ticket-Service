package com.bristle.productionticketservice.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity(name = "production_tickets")
public class ProductionTicketEntity {

    public static final String TABLE_NAME = "production_tickets";

    // I use static final variables instead of enums on purpose
    // so I can reference these strings in annotations
    public static final String TICKET_ID = "ticket_id";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String DUE_DATE = "due_date";
    public static final String BRISTLE_TYPE = "bristle_type";
    public static final String SPEC = "spec";
    public static final String INNER_TUBE_TYPE = "inner_tube_type";
    public static final String BRISTLE_DIAMETER = "bristle_diameter";
    public static final String QUANTITY = "quantity";
    public static final String ALUM_TUBE_TYPE = "aluminium_tube_type";
    public static final String ALUM_RIM_TYPE = "aluminium_tim_type";
    public static final String SPEC_NOTE = "spec_note";
    public static final String PRODUCTION_NOTE_1 = "production_note_1";
    public static final String PRODUCTION_NOTE_2 = "production_note_2";
    public static final String PRODUCTION_NOTE_3 = "production_note_3";
    public static final String PRODUCTION_NOTE_4 = "production_note_4";
    public static final String PRODUCTION_NOTE_5 = "production_note_5";
    public static final String PRODUCTION_NOTE_6 = "production_note_6";
    public static final String STATUS_ID = "status_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = TICKET_ID, nullable = false)
    private int ticketId;

    @Column(name = CUSTOMER_ID, nullable = false)
    private String customerId;

    @Column(name = DUE_DATE, nullable = true)
    private Date dueDate;

    @Column(name = BRISTLE_TYPE, nullable = true)
    private String bristleType;

    @Column(name = SPEC, nullable = true)
    private String spec;

    @Column(name = INNER_TUBE_TYPE, nullable = true)
    private String innerTubeType;

    @Column(name = BRISTLE_DIAMETER, nullable = true)
    private float bristleDiameter;

    @Column(name = QUANTITY, nullable = true)
    private int quantity;

    @Column(name = ALUM_TUBE_TYPE, nullable = true)
    private String alumTubeType;

    @Column(name = ALUM_RIM_TYPE, nullable = true)
    private String alumRimType;

    @Column(name = SPEC_NOTE, nullable = true)
    private String specNote;

    @Column(name = PRODUCTION_NOTE_1, nullable = true)
    private String ProductionNote1;

    @Column(name = PRODUCTION_NOTE_2, nullable = true)
    private String ProductionNote2;

    @Column(name = PRODUCTION_NOTE_3, nullable = true)
    private String ProductionNote3;

    @Column(name = PRODUCTION_NOTE_4, nullable = true)
    private String ProductionNote4;

    @Column(name = PRODUCTION_NOTE_5, nullable = true)
    private String ProductionNote5;

    @Column(name = PRODUCTION_NOTE_6, nullable = true)
    private String ProductionNote6;

    @Column(name = STATUS_ID, nullable = false)
    @ColumnDefault("1")
    private int statusId;

    public ProductionTicketEntity() {}
    public ProductionTicketEntity(String customerId,
                                  Date dueDate,
                                  String bristleType,
                                  String spec,
                                  String innerTubeType,
                                  float bristleDiameter,
                                  int quantity,
                                  String alumTubeType,
                                  String alumRimType,
                                  String specNote,
                                  String productionNote1,
                                  String productionNote2,
                                  String productionNote3,
                                  String productionNote4,
                                  String productionNote5,
                                  String productionNote6) {
        this.customerId = customerId;
        this.dueDate = dueDate;
        this.bristleType = bristleType;
        this.spec = spec;
        this.innerTubeType = innerTubeType;
        this.bristleDiameter = bristleDiameter;
        this.quantity = quantity;
        this.alumTubeType = alumTubeType;
        this.alumRimType = alumRimType;
        this.specNote = specNote;
        ProductionNote1 = productionNote1;
        ProductionNote2 = productionNote2;
        ProductionNote3 = productionNote3;
        ProductionNote4 = productionNote4;
        ProductionNote5 = productionNote5;
        ProductionNote6 = productionNote6;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getBristleType() {
        return bristleType;
    }

    public void setBristleType(String bristleType) {
        this.bristleType = bristleType;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getInnerTubeType() {
        return innerTubeType;
    }

    public void setInnerTubeType(String innerTubeType) {
        this.innerTubeType = innerTubeType;
    }

    public float getBristleDiameter() {
        return bristleDiameter;
    }

    public void setBristleDiameter(float bristleDiameter) {
        this.bristleDiameter = bristleDiameter;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAlumTubeType() {
        return alumTubeType;
    }

    public void setAlumTubeType(String alumTubeType) {
        this.alumTubeType = alumTubeType;
    }

    public String getAlumRimType() {
        return alumRimType;
    }

    public void setAlumRimType(String alumRimType) {
        this.alumRimType = alumRimType;
    }

    public String getSpecNote() {
        return specNote;
    }

    public void setSpecNote(String specNote) {
        this.specNote = specNote;
    }

    public String getProductionNote1() {
        return ProductionNote1;
    }

    public void setProductionNote1(String productionNote1) {
        ProductionNote1 = productionNote1;
    }

    public String getProductionNote2() {
        return ProductionNote2;
    }

    public void setProductionNote2(String productionNote2) {
        ProductionNote2 = productionNote2;
    }

    public String getProductionNote3() {
        return ProductionNote3;
    }

    public void setProductionNote3(String productionNote3) {
        ProductionNote3 = productionNote3;
    }

    public String getProductionNote4() {
        return ProductionNote4;
    }

    public void setProductionNote4(String productionNote4) {
        ProductionNote4 = productionNote4;
    }

    public String getProductionNote5() {
        return ProductionNote5;
    }

    public void setProductionNote5(String productionNote5) {
        ProductionNote5 = productionNote5;
    }

    public String getProductionNote6() {
        return ProductionNote6;
    }

    public void setProductionNote6(String productionNote6) {
        ProductionNote6 = productionNote6;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
