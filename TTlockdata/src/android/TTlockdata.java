package cordova.plugin.ttockdata;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class TTlockdata extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        else if(action.equals("lockdata")){
            callbackContext.success("lockdata"+mCurrentLock.getlockdata());
            Log.d(TAG, " lockdata " + mCurrentLock.getlockdata());
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    protected void onCreate() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mCurrentLock = mInstance().getChoosedLock();
    }

    public static TTlockdata getmInstance() {
        mInstance=this.cordova.getActivity().getApplicationContext();
        return mInstance;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public void saveChoosedLock(LockObj lockObj){
        this.mTestLockObj = lockObj;
    }

    public LockObj getChoosedLock(){
        return this.mTestLockObj;
    }
}
