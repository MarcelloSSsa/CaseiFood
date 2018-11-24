package caseifood.app.com.presenter;

import android.util.Log;

import java.util.List;

import caseifood.app.com.caseifood.api.ApiModule;
import caseifood.app.com.caseifood.api.OpenWeatherMapApi;
import caseifood.app.com.caseifood.api.payload.Weather;
import caseifood.app.com.caseifood.api.payload.WeatherResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodSuggestionPresenter {
    private final OpenWeatherMapApi weatherApi;
    private Callback listener;

    public FoodSuggestionPresenter() {
        weatherApi = ApiModule.INSTANCE.getApiInstance();
    }

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

        weatherApi.getCurrentWeatherByLocation(-22.85464, -47.05345).enqueue(new retrofit2.Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                processResult(response);
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {
                if (listener == null) { return; }

                listener.onFoodSuggestionRequestError();
            }
        });
    }

    private void processResult(Response<WeatherResult> response) {
        String foodSuggestion = "???";
        Double temperature = 0.0D;
        String weatherDescription = "???";

        try {
            WeatherResult weatherResult = response.body();
            temperature = weatherResult.getWeatherValues().getTemperature();
            if (temperature == null) {
                temperature = 0.0D;
            }

            weatherDescription = "";
            List<Weather> weathers = weatherResult.getWeatherDescription();
            if (weathers != null && !weathers.isEmpty()) {
                weatherDescription = weatherResult.getWeatherDescription().get(0).getDescription();
            }

            if (temperature > 30) {
                foodSuggestion = "Sorvete";
            } else if (temperature < 15) {
                foodSuggestion = "Pizza";
            } else {
                foodSuggestion = "Café";
            }
        } catch (Exception e){
            Log.e("ooops", "wrong...", e);

            if (listener == null) {return;}

            listener.onFoodSuggestionRequestError();
        }

        if (listener == null) { return; }

        listener.onFoodSuggestionSuccess(foodSuggestion, String.valueOf(temperature),
                weatherDescription != null && !weatherDescription.isEmpty() ? weatherDescription : "???");
    }

    public interface Callback {
        void onFoodSuggestionSuccess(String foodName, String temperature, String weatherDescriptionCode);
        void onFoodSuggestionRequestError();
    }
}
