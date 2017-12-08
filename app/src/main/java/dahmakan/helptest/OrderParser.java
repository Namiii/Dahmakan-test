package dahmakan.helptest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderParser {

	public List<OrderModel> parseOrders(JSONObject result) throws JSONException {
		ArrayList<OrderModel> orders = new ArrayList<>();
		JSONArray orderJsonArray = result.getJSONArray("orders");
		for (int i = 0; i < orderJsonArray.length(); i++) {
			JSONObject jsonObject = (JSONObject) orderJsonArray.get(i);
			OrderModel orderModel =
					new OrderModel(jsonObject.getInt("order_id"),
							jsonObject.getLong("arrives_at_utc"),
							jsonObject.getString("paid_with"));
			orders.add(orderModel);
		}
		return orders;
	}
}
