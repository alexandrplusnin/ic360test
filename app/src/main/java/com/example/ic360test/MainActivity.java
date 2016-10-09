package com.example.ic360test;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements BaseInteractor.Callback<Double> {

    /**
     * EditText для ввода значения
     */
    private EditText input;

    /**
     * Button для отправки значения на сервер
     */
    private Button send;

    /**
     * TextView с результатом
     */
    private TextView result;

    /**
     * Fragment, запускающий interactor'ы
     */
    private TaskFragment<Double> taskFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.input);
        send = (Button) findViewById(R.id.send);
        result = (TextView) findViewById(R.id.result);
        FragmentManager fm = getSupportFragmentManager();
        taskFragment = (TaskFragment<Double>) fm.findFragmentByTag(TaskFragment.TAG);

        if (taskFragment == null) {
            taskFragment = new TaskFragment<>();
            fm.beginTransaction().add(taskFragment, TaskFragment.TAG).commit();
        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    result.setText(R.string.task_started);
                    taskFragment.executeInteractor(new DivideBy2Interactor(Double.valueOf(input.getText().toString())));
                } catch (NumberFormatException exception) {
                    onFailure();
                }
            }
        });
    }

    @Override
    public void onSuccess(Double value) {
        result.setText(String.valueOf(value));
    }

    @Override
    public void onFailure() {
        result.setText(R.string.error);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
