package org.holbreich.alert;

/**
 * Kreditech imaginary Transaction.
 * 
 * @author aho
 */
public class KtTransaction {

	private String transaction_id;
	private String description;
	private double value;
	private String date;

	public KtTransaction(String transaction_id, String description, double value, String date) {
		super();
		this.transaction_id = transaction_id;
		this.description = description;
		this.value = value;
		this.date = date;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public String getDescription() {
		return description;
	}

	public double getValue() {
		return value;
	}

	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", description=" + description + ", value=" + value
				+ ", date=" + date + "]";
	}

}
