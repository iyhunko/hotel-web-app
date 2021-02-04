package com.iyhunko.hotel.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "sum")
    private Integer sum;

    @Column(name = "currency")
    private String currency;

    /**
     * TODO: should be Enum
     */
    @Column(name = "status")
    private String status;

    @Column(name = "expire_at")
    private Timestamp expireAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Timestamp expireAt) {
        this.expireAt = expireAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
