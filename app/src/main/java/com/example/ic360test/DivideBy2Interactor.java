package com.example.ic360test;

import android.os.AsyncTask;

/**
 * Created by Alexandr on 09.10.2016.
 * <p>
 * Интерактор, выполняющий деление на 2
 */
class DivideBy2Interactor extends BaseInteractor<Double> {

    /**
     * Значение, которое необходимо разделить на 2
     */
    private final double value;

    /**
     * Конструктор
     *
     * @param value значение, которое нужно разделить
     */
    DivideBy2Interactor(double value) {
        super();
        this.value = value;
    }

    @Override
    boolean isCached() {
        return SharedPreferencesManager.getValue(callback.getContext(), String.valueOf(value), null) != null;
    }

    @Override
    Double getResult() {
        String result = SharedPreferencesManager.getValue(callback.getContext(), String.valueOf(value), null);
        return result != null ? Double.valueOf(result) : null;
    }

    @Override
    AsyncTask<Void, Void, Double> getAsyncTask() {
        return new AsyncTask<Void, Void, Double>() {
            @Override
            protected Double doInBackground(Void... params) {
                return server.divideBy2(value);
            }

            @Override
            protected void onPostExecute(Double aDouble) {
                SharedPreferencesManager.setValue(callback.getContext(), String.valueOf(value), String.valueOf(aDouble));
                callback.onSuccess(getResult());
            }
        };
    }
}
