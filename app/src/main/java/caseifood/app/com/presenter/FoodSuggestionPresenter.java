package caseifood.app.com.presenter;

public class FoodSuggestionPresenter {
    private Callback listener;

    public void attachView(Callback listener) {
        this.listener = listener;
    }

    public void detachView() {
        this.listener = null;
    }

    public void requestFoodSuggestion() {
        if (listener == null) {
            return;
        }

        listener.onFoodSuggestionSuccess("Hot-dog", "25", 2);
    }

    public interface Callback {
        void onFoodSuggestionSuccess(String foodName, String temperature, int weatherDescriptionCode);
        void onFoodSuggestionRequestError();
    }
}
