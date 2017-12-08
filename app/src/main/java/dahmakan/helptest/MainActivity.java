package dahmakan.helptest;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OrderAdapterClickListener {
	private HelpScreenPresenter mPresenter;
	private OrderAdapter mOrderAdapter;
	private TextView mErrorView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		TextView toolBarTitle = toolbar.findViewById(R.id.toolbar_title);
		toolBarTitle.setText(getString(R.string.help_screen_title));
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			getSupportActionBar().setTitle("");
		}

		mErrorView = findViewById(R.id.help_screen_error_View);
		mErrorView.setOnClickListener(view -> {
			if (mPresenter == null)
				return;

			mPresenter.getOrders();
		});

		RecyclerView mRecyclerView = findViewById(R.id.help_screen_recycler_view);
		mOrderAdapter = new OrderAdapter(this);
		mRecyclerView.setAdapter(mOrderAdapter);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
		mRecyclerView.addItemDecoration(new CustomItemDecorator(this));
		//This is the part I was working on for the indicators, code is by someone else. Did not get enough time to
		//finish this part
		//Also wanted to point out, I chose to go with Recyclerview here but if Viewpager is used, these dot indicators can be
		//achieved much easier.
//		mRecyclerView.addItemDecoration(new LinePagerIndicatorDecoration());

		PagerSnapHelper snapHelper = new PagerSnapHelper();
		snapHelper.attachToRecyclerView(mRecyclerView);

		//Would have used dagger here, but it would take too much time
		OrderRepository mRepository = new OrderRepositoryImpl(VolleySingleton.getInstance(this));
		mPresenter = new HelpScreenPresenterImpl(mRepository);

		mPresenter.getOrders();
	}


	public void setOrders(List<OrderModel> orders) {
		mOrderAdapter.setOrders(orders);
	}

	@Override
	protected void onStart() {
		super.onStart();
		mPresenter.start(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		mPresenter.stop();
	}

	public void toggleErrorView(boolean show) {
		mErrorView.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void itemSelected(int orderId) {
		//This is where opening the next screen logic would go
//		Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//		intent.putExtra("order_id", orderId);
//		startActivity(intent);
		Toast.makeText(this, "Item with order Id " + orderId + " selected", Toast.LENGTH_LONG).show();
	}
}
