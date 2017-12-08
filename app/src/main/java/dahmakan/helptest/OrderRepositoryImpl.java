package dahmakan.helptest;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONException;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
	private VolleySingleton mVolleySingleton;

	OrderRepositoryImpl(VolleySingleton mVolleySingleton) {
		this.mVolleySingleton = mVolleySingleton;
	}

	@Override
	public void getOrders(final OrderCallback orderCallback) {
		JsonRequest<org.json.JSONObject> request = new JsonObjectRequest(
				Request.Method.GET,
				Constants.BASE_URL + "orders",
				null,
				response -> {
					OrderParser parser = new OrderParser();
					try {
						//This could be done better with gson (without having a whole parser class)
						//but this was just handy because of time restriction
						List<OrderModel> orders = parser.parseOrders(response);
						orderCallback.onOrdersDelivered(orders);
					} catch (JSONException e) {
						e.printStackTrace();
						orderCallback.onOrdersDeliveryFailed(new VolleyError("Parse error"));
					}
				},
				orderCallback::onOrdersDeliveryFailed);
		request.setTag(this);
		mVolleySingleton.cancelAllRequests(this);
		mVolleySingleton.addToRequestQueue(request);
	}

	@Override
	public void cancelOrders() {
		mVolleySingleton.cancelAllRequests(this);
	}
}