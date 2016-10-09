package com.example.ic360test;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Alexandr on 09.10.2016.
 * <p>
 * Базовый интерактор
 */
abstract class BaseInteractor<Result> {

    /**
     * Реализация сервера
     */
    Server server;

    /**
     * Callback
     */
    Callback<Result> callback;

    /**
     * AsyncTask
     */
    private AsyncTask<Void, Void, Result> task = null;

    /**
     * Конструктор
     */
    BaseInteractor() {
        server = MockServer.getInstance();
    }

    /**
     * Выполняет работу
     */
    final void execute(Callback<Result> callback) {
        this.callback = callback;
        if (isCached()) {
            this.callback.onSuccess(getResult());
        } else {
            task = getAsyncTask();
            task.execute();
        }
    }

    /**
     * Отменяет текущий AsyncTask
     */
    void cancel() {
        if (task != null) {
            task.cancel(true);
        }
    }

    /**
     * @return true, если данные есть в кеше, иначе false
     */
    abstract boolean isCached();

    /**
     * @return данные
     */
    abstract Result getResult();

    /**
     * Выполняет асинхронный запрос
     */
    abstract AsyncTask<Void, Void, Result> getAsyncTask();

    /**
     * Интерфейс, который должны реализовывать пользователи interactor'а
     */
    interface Callback<Result> {

        /**
         * Вызывается в случае успеха
         *
         * @param result данные
         */
        void onSuccess(Result result);

        /**
         * Вызывается в случае ошибки
         */
        void onFailure();

        /**
         * @return Context
         */
        Context getContext();
    }
}
