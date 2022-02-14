package com.virtusa.customerapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
@Table(name="Address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Address_Id")
	private long addressId;
	@Enumerated(EnumType.STRING)
	@Column(name="Address_Type",nullable = false,length = 50)
	private AddressType addressType;
    @Column(name="Door_No",nullable = false,length = 8)
	private String doorNo;
    @Column(name="Street_Name",nullable = false,length = 100)
	private String streetName;
    @Column(name="City",nullable = false,length = 100)
    private String city;
    @Column(name="State",nullable = false,length = 100)
	private String state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "Customer_Id"),name = "Customer_Id")
	@JsonIgnore
    private Customer customer;

}
