package org.holbreich.alert;

import org.springframework.stereotype.Component;

/**
 * 
 * Output example:
 * <pre>
 *  Found transaction over alert threshold:
 *  description: Weird transaction,
 *  value: -10025.00,
 *  date: 2019-09-04
 * </pre>
 * 
 * @author aho
 *
 */
@Component
public class StdOutAlertPrinter implements WeirdTransactionAlerter {

	
	@Override
	public void alert(KtTransaction transaction) {
		System.out.println("Found transaction over alert threshold:");
		System.out.println("description: Weird transaction,");
		System.out.println("value: "+transaction.getValue() +",");
		System.out.println("date: "+transaction.getDate());
	}

}
