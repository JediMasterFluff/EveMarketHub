package ca.prairesunapplications.evemarkethub.utils;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static AppController instance;

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

    public static  synchronized AppController getInstance(){
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        return mRequestQueue;
    }

    public ImageLoader getmImageLoader(){
        getRequestQueue();
        if(mImageLoader == null)
            mImageLoader = new ImageLoader(this.mRequestQueue, new LruBitmapCache());

        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag){
        req.setTag(TextUtils.isEmpty(tag)? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request <T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag){
        if(mRequestQueue != null)
            mRequestQueue.cancelAll(TAG);
    }
}
