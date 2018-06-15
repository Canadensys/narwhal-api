package net.canadensys.api.narwhal.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Application configurations
 */
public class NarwhalConfiguration {

    public static final String PAGE_ROOT_MODEL_KEY = "page";
    public static final String LANG_PARAM = "lang";

    public static final List<String> SUPPORTED_LANGUAGES = new ArrayList<>();
    static{
        SUPPORTED_LANGUAGES.add("en");
        SUPPORTED_LANGUAGES.add("fr");
    }

    private String currentVersion;
    private String googleAnalyticsSiteVerification;
    private String googleAnalyticsAccount;
    private String feedbackURL;

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

    public String getFeedbackURL() {
        return feedbackURL;
    }
    public void setFeedbackURL(String feedbackURL) {
        this.feedbackURL = feedbackURL;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }
    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }
}
