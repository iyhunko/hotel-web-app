package com.iyhunko.hotel.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "price_from")
    private Float priceFrom;

    @Column(name = "price_to")
    private Float priceTo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "checkin_date")
    private Date checkinDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "checkout_date")
    private Date checkoutDate;

    private Integer floor;

    @Column(name = "class")
    private String roomClass;

    @Column(name = "room_size")
    private Integer size;

    @Column(name = "rooms_quantity")
    private Integer roomsQuantity;

    @Column(name = "adults_quantity")
    private Integer adultsQuantity;

    @Column(name = "children_quantity")
    private Integer childrenQuantity;

    @Column(name = "bedsQuantity")
    private Integer bedsQuantity;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "has_bar", columnDefinition = "TINYINT")
    private Boolean hasBar = false;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "has_tv", columnDefinition = "TINYINT")
    private Boolean hasTv = false;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "has_fridge", columnDefinition = "TINYINT")
    private Boolean hasFridge = false;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "has_balcony", columnDefinition = "TINYINT")
    private Boolean hasBalcony = false;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_smoking_allowed", columnDefinition = "TINYINT")
    private Boolean isSmokingAllowed = false;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "are_pets_allowed", columnDefinition = "TINYINT")
    private Boolean arePetsAllowed = false;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    private Date createdAt;

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Float getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Float priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Float getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Float priceTo) {
        this.priceTo = priceTo;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getRoomsQuantity() {
        return roomsQuantity;
    }

    public void setRoomsQuantity(Integer roomsQuantity) {
        this.roomsQuantity = roomsQuantity;
    }

    public Integer getAdultsQuantity() {
        return adultsQuantity;
    }

    public void setAdultsQuantity(Integer adultsQuantity) {
        this.adultsQuantity = adultsQuantity;
    }

    public Integer getChildrenQuantity() {
        return childrenQuantity;
    }

    public void setChildrenQuantity(Integer childrenQuantity) {
        this.childrenQuantity = childrenQuantity;
    }

    public Integer getBedsQuantity() {
        return bedsQuantity;
    }

    public void setBedsQuantity(Integer bedsQuantity) {
        this.bedsQuantity = bedsQuantity;
    }

    public Boolean getHasBar() {
        return hasBar;
    }

    public void setHasBar(Boolean hasBar) {
        this.hasBar = hasBar;
    }

    public Boolean getHasTv() {
        return hasTv;
    }

    public void setHasTv(Boolean hasTv) {
        this.hasTv = hasTv;
    }

    public Boolean getHasFridge() {
        return hasFridge;
    }

    public void setHasFridge(Boolean hasFridge) {
        this.hasFridge = hasFridge;
    }

    public Boolean getHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(Boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public Boolean getIsSmokingAllowed() {
        return isSmokingAllowed;
    }

    public void setIsSmokingAllowed(Boolean smokingAllowed) {
        isSmokingAllowed = smokingAllowed;
    }

    public Boolean getArePetsAllowed() {
        return arePetsAllowed;
    }

    public void setArePetsAllowed(Boolean arePetsAllowed) {
        this.arePetsAllowed = arePetsAllowed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}
