package dahmakan.helptest;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

@SuppressLint("StaticFieldLeak")
class VolleySingleton {
	private static VolleySingleton mInstance;
	private RequestQueue mRequestQueue;
	private static Context mContext;


	private VolleySingleton(Context context) {
		mContext = context.getApplicationContext();
		mRequestQueue = getRequestQueue();
		VolleyLog.DEBUG = true;
	}

	static synchronized VolleySingleton getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new VolleySingleton(context);
		}

		return mInstance;
	}

	private RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(mContext);
		}

		return mRequestQueue;
	}


	<T> void addToRequestQueue(Request<T> req) {
		getRequestQueue().add(req);
	}

	void cancelAllRequests(Object tag) {
		getRequestQueue().cancelAll(tag);
	}
}