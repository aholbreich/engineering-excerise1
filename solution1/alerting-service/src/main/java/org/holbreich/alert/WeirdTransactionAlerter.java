package org.holbreich.alert;

/**
 * Alerter is responsible for the 'alerting logic'.
 * Alerting happens when 'weired transactions' are detected.
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
