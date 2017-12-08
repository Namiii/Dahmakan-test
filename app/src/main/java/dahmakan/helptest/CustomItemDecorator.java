package dahmakan.helptest;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class CustomItemDecorator extends RecyclerView.ItemDecoration {
	private int mMargin;

	public CustomItemDecorator(Context context) {
		mMargin = context.getResources().getDimensionPixelSize(R.dimen.main_padding);
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);
		outRect.top = mMargin;
		outRect.right = mMargin;
		outRect.bottom = mMargin;
		outRect.left = mMargin;
	}
}