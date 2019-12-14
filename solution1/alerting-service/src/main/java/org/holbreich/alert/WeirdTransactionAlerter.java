package org.holbreich.alert;

/**
 * Alerter is reponsible for the alerting logic on the detection of "weired transaction".
 * 
 * @author aho
 *
 */
public interface WeirdTransactionAlerter {

	/**
	 * Is called to trigger an alert.
	 * @param transaction
	 */
	void alert(KtTransaction transaction);

}
