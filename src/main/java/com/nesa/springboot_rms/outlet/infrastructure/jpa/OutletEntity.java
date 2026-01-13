package com.nesa.springboot_rms.outlet.infrastructure.jpa;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "outlets")
@Data
public class OutletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /* =========================
       Parent Relationship
       ========================= */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hotel_id", nullable = false)
    private HotelEntity hotel;

    /* =========================
       Core Details
       ========================= */
    @Column(name = "outlet_code", unique = true, nullable = false, length = 50)
    private String outletCode;

    @Column(name = "outlet_name", nullable = false, length = 255)
    private String outletName;

    @Column(name = "outlet_type", length = 50)
    private String outletType; // restaurant, bar, spa, shop

    /* =========================
       Location & Contact
       ========================= */
    @Column(name = "address_line1", length = 255)
    private String addressLine1;

    @Column(name = "address_line2", length = 255)
    private String addressLine2;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "state", length = 100)
    private String state;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "email", length = 150)
    private String email;

    /* =========================
       Operational Info
       ========================= */
    @Column(name = "opening_time", length = 10)
    private String openingTime;

    @Column(name = "closing_time", length = 10)
    private String closingTime;

    @Column(name = "timezone", length = 50)
    private String timezone;

    @Column(name = "opening_date")
    private LocalDate openingDate;

    @Column(name = "closing_date")
    private LocalDate closingDate;

    /* =========================
       Status & Flags
       ========================= */
    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "status", length = 30)
    private String status; // ACTIVE, INACTIVE, SUSPENDED

}