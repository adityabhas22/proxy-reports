package com.example.reports;

/**
 * TODO (student):
 * Implement Proxy responsibilities here:
 * - access check
 * - lazy loading
 * - caching of RealReport within the same proxy
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();
    private RealReport report; 


    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // Starter placeholder: intentionally incorrect.
        if(!accessControl.canAccess(user, classification)) {
            System.out.println("User " + user.getName() + " is not authorized to access report " + reportId);
            return;
        } //First we do access check and only then we load the report
        if(report == null) {
            report = new RealReport(reportId, title, classification);
        }
        // Students should remove direct real loading on every call.
        report.display(user);
    }
}
