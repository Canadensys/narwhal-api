package net.canadensys.api.narwhal.config;


/**
 * API Configuration class
 * @author canadensys
 *
 */
public class APIConfiguration {
		
	private String googleAnalyticsSiteVerification;
	private String googleAnalyticsAccount;
	
	public String getGoogleAnalyticsAccount() {
		return googleAnalyticsAccount;
	}
	public void setGoogleAnalyticsAccount(String googleAnalyticsAccount) {
		this.googleAnalyticsAccount = googleAnalyticsAccount;
	}

	public String getGoogleAnalyticsSiteVerification() {
		return googleAnalyticsSiteVerification;
	}

	public void setGoogleAnalyticsSiteVerification(
			String googleAnalyticsSiteVerification) {
		this.googleAnalyticsSiteVerification = googleAnalyticsSiteVerification;
	}
}
