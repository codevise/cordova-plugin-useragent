package de.codevise.useragent;

import android.webkit.WebSettings;
import android.webkit.WebView;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;

public class UserAgent extends CordovaPlugin {

    private WebSettings settings;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        final WebView mWebView = ((WebView) webView.getEngine().getView());

        try {
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    settings = mWebView.getSettings();
                }
            });
        } catch (Exception e) {
            settings = null;
        }
    }

    @Override
    public boolean execute(String action,
                           JSONArray args,
                           CallbackContext callbackContext) {
        try {
            switch (action) {
                case "get":
                    callbackContext.success(settings.getUserAgentString());
                    return true;
                case "set":
                    String userAgentString = args.optString(0);
                    settings.setUserAgentString(userAgentString);
                    callbackContext.success(settings.getUserAgentString());
                    return true;
                case "reset":
                    settings.setUserAgentString(null);
                    callbackContext.success(settings.getUserAgentString());
                    return true;
            }
            callbackContext.error("Invalid action");
            return false;
        } catch (Exception e) {
            callbackContext.error(e.getMessage());
            return false;
        }
    }
}