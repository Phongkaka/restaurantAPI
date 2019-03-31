package com.restaurant.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "delivery_track")
public class DeliveryTrack extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "estimated_time")
    private Date estimatedTime;

    @Column(name = "bill_id")
    private int billId;

    /**
     * value 1 : confirm order
     * value 2 : delivery order
     * value 3 : successful delivery
     */
    @Min(value = 1, message = "Enter 1 to 3")
    @Max(value = 3, message = "Enter 1 to 3")
    @Column(name = "status")
    private int status;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bill_id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Bill bill;

    public DeliveryTrack() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Date estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
