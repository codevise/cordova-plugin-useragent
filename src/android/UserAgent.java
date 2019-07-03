package de.codevise.useragent;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.webkit.WebSettings;
import android.webkit.WebView;

public class UserAgent extends CordovaPlugin {

    public WebSettings settings;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        try {
            settings = ((WebView) webView.getEngine().getView()).getSettings();
        } catch (Exception e) {
            settings = null;
        }
    }

    @Override
    public boolean execute(String action,
                           JSONArray args,
                           CallbackContext callbackContext) throws JSONException {
        try {
            if (action.equals("get")) {
                callbackContext.success(settings.getUserAgentString());
                return true;
            } else if (action.equals("set")) {
                String userAgentString = args.optString(0);
                settings.setUserAgentString(userAgentString);
                callbackContext.success(settings.getUserAgentString());
                return true;
            } else if (action.equals("reset")) {
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