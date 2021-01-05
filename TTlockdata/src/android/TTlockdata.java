package cordova.plugin.ttlockdata;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.ttlock.bl.sdk.api.ExtendedBluetoothDevice;
import com.ttlock.bl.sdk.entity.LockError;
import com.ttlock.bl.sdk.api.TTLockClient;
import com.ttlock.bl.sdk.callback.ScanLockCallback;
import com.ttlock.bl.sdk.callback.InitLockCallback;
import com.ttlock.bl.sdk.util.FeatureValueUtil;
import com.ttlock.bl.sdk.constant.FeatureValue;
import com.ttlock.bl.sdk.callback.SetNBServerCallback;
import org.apache.cordova.*;
import android.content.Context;
import android.util.Log;
import android.support.v4.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Locale;
//import ttlock.demo.DateUtils;
//import ttlock.demo.lock.adapter.LockListAdapter;
/**
 * This class echoes a string called from JavaScript.
 */
public class TTlockdata extends CordovaPlugin {
    public static TTlockdata instance = null;
    private  final String TAG = "TTLockData";
    public  CordovaWebView cordovaWebView;
   public CordovaInterface cordovaInterface;
   public ExtendedBluetoothDevice device1;
   protected static final int REQUEST_PERMISSION_REQ_CODE=11;

    @Override
   public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if(action.equals("init")){
            Context context=this.cordova.getActivity().getApplicationContext();
            TTLockClient.getDefault().prepareBTService(context);
            callbackContext.success("lockdata no ans");
            Log.d(TAG, " lockdata no ans ");
            return true;
       }
       else if(action.equals("lockscan")){
        startScan(callbackContext);
        return true;
       }
 else if(action.equals("lockdata")){
       initializeLock(callbackContext);
      
         return true;
    }
       return false;
    } 



    public void startScan(CallbackContext callbackContext){
        TTLockClient.getDefault().startScanLock(new ScanLockCallback() {
            @Override
            public void onScanLockSuccess(ExtendedBluetoothDevice device) {
                if(device != null) {
                    if(device.isSettingMode()){
                 device1=device;
                callbackContext.success("ld scan  "+device);
                Log.d(TAG, "ld data   "+device);
                    }
                }
            }
            @Override
            public void onFail(LockError error) {
                callbackContext.success("ld scan fail  "+error);
                Log.d(TAG, "ld data fail  "+error);
            }
        });
    }
     
    initializeLock(CallbackContext callbackContext) {
        callbackContext.success("ld data to send    "+device1);
        TTLockClient.getDefault().initLock(device1, new InitLockCallback() {
            @Override
            public void onInitLockSuccess(String lockData) {
                callbackContext.success("ld data  "+lockData);
                if(FeatureValueUtil.isSupportFeature(lockData, FeatureValue.NB_LOCK)){
                    //callbackContext.success("ld data  "+lockData);
           Log.d(TAG, "ld data   "+lockData);
                }
            }
         
            @Override
            public void onFail(LockError error) {
                //failed   
                callbackContext.success("ld data fail "+error);
       Log.d(TAG, "ld data fail  "+error);             
            }
        });
    }
}
