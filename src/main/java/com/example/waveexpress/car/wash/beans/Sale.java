package com.example.waveexpress.car.wash.beans;

import jakarta.persistence.*;
import lombok.*;

/**
 * Model class representing sales data.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id; // Unique identifier for the sale
	
	private String date; // Date of the sale
	private double salesAmt; // Amount of sales
	private int washes; // Number of washes
	private String enteredBy; // User who entered the sale data

	/**
	 * Parameterized constructor.
	 * 
	 * @param date the date of the sale
	 * @param salesAmt the amount of sales
	 * @param washes the number of washes
	 * @param enteredBy the user who entered the sale data
	 */
	public Sale(String date, double salesAmt, int washes, String enteredBy){
		this.date = date;
		this.salesAmt = salesAmt;
		this.washes = washes;
		this.enteredBy = enteredBy;
	}
}
