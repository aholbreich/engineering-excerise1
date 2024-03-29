package org.holbreich.alert;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.stereotype.Component;

/**
 * 
 * Output example:
 * 
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

	private NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);

	public StdOutAlertPrinter() {
		super();

		formatter.setMinimumFractionDigits(2);
		formatter.setGroupingUsed(false);
	}

	@Override
	public void alert(KtTransaction transaction) {

		System.out.println("Found transaction over alert threshold:");
		System.out.println("description: Weird transaction,");
		System.out.println("value: " + formatter.format(transaction.getValue()) + ",");
		System.out.println("date: " + transaction.getDate());
	}

}
