package dahmakan.helptest;

public interface BasePresenter<ViewT> {
	void start(ViewT view);

	void stop();
}
