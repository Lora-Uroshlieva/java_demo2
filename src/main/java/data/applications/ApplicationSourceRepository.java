package data.applications;

public class ApplicationSourceRepository {
    private ApplicationSourceRepository() {}

    public static IApplicationSource defaultParameters() {
        return OpencarttChrome();
    }

    public static IApplicationSource OpencarttChrome() {
        return new ApplicationSource(
                "ChromeTemporary",
                "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe",
                "http://opencartt.rf.gd",
                "http://opencartt.rf.gd/index.php?route=product/search");
    }

    public static IApplicationSource OpencarttFirefox() {
        return new ApplicationSource(
                "FirefoxTemporary",
                "C:/Program Files/Mozilla Firefox/browser/geckodriver.exe",
                "http://opencartt.rf.gd",
                "http://opencartt.rf.gd/index.php?route=product/search");
    }

}
