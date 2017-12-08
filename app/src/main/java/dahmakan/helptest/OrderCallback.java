package dahmakan.helptest;

import com.android.volley.VolleyError;

import java.util.List;


public interface OrderCallback {
	void onOrdersDelivered(List<OrderModel> orders);
	void onOrdersDeliveryFailed(VolleyError error);
}
