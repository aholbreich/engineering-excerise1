package org.holbreich;

public class Transaction {
	//transaction_id,description,value,date
	
	private String transaction_id;
	private String description;
	private String value;
	private String date;
	
	public Transaction(String transaction_id, String description, String value, String date) {
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

	public String getValue() {
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
