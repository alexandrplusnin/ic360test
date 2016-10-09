package com.example.ic360test;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Alexandr on 09.10.2016.
 * <p>
 * Этот фрагмент хранит в себе запущенный interactor.
 * Он сохраняет своё состояние при повороте экрана.
 */
public class TaskFragment<Result> extends Fragment implements BaseInteractor.Callback<Result> {

    /**
     * Тег
     */
    static final String TAG = TaskFragment.class.getSimpleName();

    /**
     * Активити, реализующая Callback
     */
    private BaseInteractor.Callback<Result> callback;

    /**
     * Interactor, с которым работаем
     */
    private BaseInteractor<Result> interactor = null;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        callback = (BaseInteractor.Callback<Result>) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /**
     * Запускает работу interactor'а
     *
     * @param interactor interactor
     */
    public void executeInteractor(BaseInteractor<Result> interactor) {
        if (this.interactor != null) {
            this.interactor.cancel();
        }
        this.interactor = interactor;
        interactor.execute(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onSuccess(Result value) {
        if (callback != null) {
            callback.onSuccess(value);
        }
    }

    @Override
    public void onFailure() {
        if (callback != null) {
            callback.onFailure();
        }
    }
}