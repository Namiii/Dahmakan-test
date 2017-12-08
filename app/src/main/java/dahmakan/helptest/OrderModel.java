package dahmakan.helptest;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderModel implements Parcelable {

	private int orderId;
	private long orderTimeStamp;
	private String paymentMethod;

	OrderModel(int orderId, long orderTimeStamp, String paymentMethod) {
		this.orderId = orderId;
		this.orderTimeStamp = orderTimeStamp;
		this.paymentMethod = paymentMethod;
	}

	int getOrderId() {
		return orderId;
	}

	long getOrderTimeStamp() {
		return orderTimeStamp;
	}

	String getPaymentMethod() {
		return paymentMethod;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.orderId);
		dest.writeLong(this.orderTimeStamp);
		dest.writeString(this.paymentMethod);
	}

	private OrderModel(Parcel in) {
		this.orderId = in.readInt();
		this.orderTimeStamp = in.readLong();
		this.paymentMethod = in.readString();
	}

	public static final Creator<OrderModel> CREATOR = new Creator<OrderModel>() {
		@Override
		public OrderModel createFromParcel(Parcel source) {
			return new OrderModel(source);
		}

		@Override
		public OrderModel[] newArray(int size) {
			return new OrderModel[size];
		}
	};
}
