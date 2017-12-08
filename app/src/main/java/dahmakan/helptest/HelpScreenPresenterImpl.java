package dahmakan.helptest;

import com.android.volley.VolleyError;

import java.util.Collections;
import java.util.List;

public class HelpScreenPresenterImpl implements HelpScreenPresenter {
	private MainActivity mView;
	private OrderRepository mOrderRepository;

	HelpScreenPresenterImpl(OrderRepository mOrderRepository) {
		this.mOrderRepository = mOrderRepository;
	}

	@Override
	public void start(MainActivity view) {
		mView = view;
	}

	@Override
	public void stop() {
		mView = null;
		mOrderRepository.cancelOrders();
	}

	@Override
	public void getOrders() {
		if (mOrderRepository == null)
			return;

		mOrderRepository.getOrders(new OrderCallback() {
			@Override
			public void onOrdersDelivered(List<OrderModel> orders) {
				if (mView == null)
					return;

				if (orders != null && orders.size() > 0) {
					Collections.sort(orders, (orderModel1, orderModel2) -> Long.compare(orderModel1.getOrderTimeStamp(), orderModel2.getOrderTimeStamp()));
					mView.toggleErrorView(false);
					mView.setOrders(orders);
				} else {
					//In case of empty array, give the ability to user to try again
					mView.toggleErrorView(true);
				}
			}

			@Override
			public void onOrdersDeliveryFailed(VolleyError error) {
				if (mView == null)
					return;
				mView.toggleErrorView(true);
			}
		});
	}
}
