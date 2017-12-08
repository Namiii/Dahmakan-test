package dahmakan.helptest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
	private OrderAdapterClickListener mListener;
	private List<OrderModel> mOrders = new ArrayList<>();

	OrderAdapter(OrderAdapterClickListener mListener) {
		this.mListener = mListener;
	}

	void setOrders(List<OrderModel> orders){
		this.mOrders = orders;
		notifyDataSetChanged();
	}

	@Override
	public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.order_item_bg, parent, false);
		return new OrderViewHolder(view);
	}

	@Override
	public void onBindViewHolder(OrderViewHolder holder, int position) {
		OrderModel orderModel = mOrders.get(position);
		holder.mOrderTextView.setText(String.format("#%s", orderModel.getOrderId()));
		//Added date to the text to be able to clearly distinguish sorting (was confusing with just time)
		holder.mTimeTextView.setText(Helper.getStringTime(orderModel.getOrderTimeStamp()));
		holder.mPaymentMethodTextView.setText(orderModel.getPaymentMethod());
	}

	@Override
	public int getItemCount() {
		return mOrders.size();
	}


	class OrderViewHolder extends RecyclerView.ViewHolder {
		private TextView mOrderTextView;
		private TextView mTimeTextView;
		private TextView mPaymentMethodTextView;

		OrderViewHolder(View itemView) {
			super(itemView);
			mOrderTextView = itemView.findViewById(R.id.order_id_tv);
			mTimeTextView = itemView.findViewById(R.id.order_time_tv);
			mPaymentMethodTextView = itemView.findViewById(R.id.order_payment_tv);

			itemView.setOnClickListener(view -> {
				if(mListener == null)
					return;

				mListener.itemSelected(mOrders.get(getAdapterPosition()).getOrderId());
			});
		}
	}

}
