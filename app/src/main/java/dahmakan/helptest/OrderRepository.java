package dahmakan.helptest;


public interface OrderRepository {
	void getOrders(OrderCallback orderCallback);
	void cancelOrders();
}
