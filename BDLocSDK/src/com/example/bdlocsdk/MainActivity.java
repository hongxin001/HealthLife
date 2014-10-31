package com.example.bdlocsdk;

import com.baidu.navisdk.BNaviEngineManager.NaviEngineInitListener;
import com.baidu.navisdk.BaiduNaviManager;
import com.baidu.navisdk.ui.routeguide.BNavigator;
import com.baidu.nplatform.comapi.map.MapGLSurfaceView;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {
	private boolean mIsEngineInitSuccess = false;  
	private NaviEngineInitListener mNaviEngineInitListener = new NaviEngineInitListener() {  
	        public void engineInitSuccess() {  
	            //导航初始化是异步的，需要一小段时间，以这个标志来识别引擎是否初始化成功，为true时候才能发起导航  
	            mIsEngineInitSuccess = true;  
	        }  
	 
	        public void engineInitStart() {  
	        }  
	 
	        public void engineInitFail() {  
	        }  
	    };  
	private String getSdcardDir() {  
	        if (Environment.getExternalStorageState().equalsIgnoreCase(  
	                Environment.MEDIA_MOUNTED)) {  
	            return Environment.getExternalStorageDirectory().toString();  
	        }  
	        return null;  
	    }       
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
      //初始化导航引擎  
        BaiduNaviManager.getInstance().  
            initEngine(this, getSdcardDir(), mNaviEngineInitListener, "我的key",null);
        MapGLSurfaceView nMapView = BaiduNaviManager.getInstance().createNMapView(this);  
        View navigatorView = BNavigator.getInstance().init(this, getIntent().getExtras(), nMapView);  
        setContentView(navigatorView);
    }

}
