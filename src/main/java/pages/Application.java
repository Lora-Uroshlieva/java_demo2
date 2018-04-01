package pages;

import data.applications.ApplicationSourceRepository;
import data.applications.IApplicationSource;
import pages.utils.BrowserWrapper;

public class Application {
    private static volatile Application instance;
    private IApplicationSource applicationSource;
    private BrowserWrapper browser;

    public Application(IApplicationSource applicationSource) {
        this.applicationSource = applicationSource;
    }

    public static Application get() {
        return get(null);
    }

    public static Application get(IApplicationSource applicationSource) {
        if (instance == null) {
            synchronized (Application.class) {
                if (instance == null) {
                    if (applicationSource == null) {
                        applicationSource = ApplicationSourceRepository.defaultParameters();
                    }
                    instance = new Application(applicationSource);
                    instance.initBrowser(applicationSource);
                }
            }
        }
        return instance;
    }

    public static void remove() {
        if (instance != null) {
            instance.getBrowser().quit();
            instance = null;
        }
    }

    public IApplicationSource getApplicationSource() {
        return applicationSource;
    }

    public BrowserWrapper getBrowser() {
        return browser;
    }

    private void initBrowser(IApplicationSource applicationSource) {
        this.browser = new BrowserWrapper(applicationSource);
    }

    //Pages Loading ----------------------------------------------------------------------------------------
    public HomePage loadHomePage() {
        getBrowser().getDriver().get(applicationSource.getBaseUrl());
        return new HomePage();
    }

    public SearchPage loadSearchPage() {
        getBrowser().getDriver().get(applicationSource.getSearchPageUrl());
        return new SearchPage();
    }
}
