
public class TestSession {
    enum Browser {CHROME, FIREFOX, EDGE}
    private static int sessionCount = 0;

    private int sessionId;  //instance
    private Browser browser;
    private long startTime;

    public TestSession(Browser browser) {

        sessionCount++;

        this.sessionId = sessionCount;
        this.browser = browser;
        this.startTime = System.currentTimeMillis();
    }

    public static int getSessionCount() {
        return sessionCount;
    }


    public String getInfo() {  // Instance
        return "Session ID: " + sessionId
                + " , Browser: " + browser
                + " , Start Time: " + startTime;
    }

    public static void main(String[] args) {

        System.out.println("Initial Count: "
                + TestSession.getSessionCount());

        TestSession s1 = new TestSession(Browser.CHROME);
        TestSession s2 = new TestSession(Browser.FIREFOX);
        TestSession s3 = new TestSession(Browser.EDGE);

        System.out.println(s1.getInfo());
        System.out.println(s2.getInfo());
        System.out.println(s3.getInfo());

        int total = TestSession.getSessionCount();

        System.out.println("Total Sessions: " + total);
        System.out.println("Count Test Passed: " + (total == 3));
    }
}
