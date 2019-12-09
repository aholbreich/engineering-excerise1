package org.holbreich.alert;

/**
 * Alerter is reponsible for the alerting logic on the detection of weired transaction.
 * @author aho
 *
 */
public interface WeirdTransactionAlerter {

	
	void alert(KtTransaction transaction);

}
