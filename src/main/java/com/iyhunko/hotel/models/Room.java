package com.iyhunko.hotel.models;

import com.iyhunko.hotel.enums.RoomStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RoomStatus status;

    private String title;

    private Float price;

    @Column(nullable = false, columnDefinition = "text")
    @Type(type = "text")
    private String description;

    @Column(columnDefinition = "text")
    @Type(type = "text")
    private String photo;

    private Integer floor;

    @Column(name = "class")
    private String roomClass;

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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }
}
