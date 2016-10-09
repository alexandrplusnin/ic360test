package com.example.ic360test;

import android.util.Log;

/**
 * Created by Alexandr on 09.10.2016.
 * <p>
 * Mock-реализация сервера
 */
class MockServer implements Server {

    /**
     * Время задержки по умолчанию
     */
    private static final long SLEEP_TIME = 10 * 1000;

    /**
     * Время задержки
     */
    private long sleepTime = SLEEP_TIME;

    /**
     * Инстанс синглтона
     */
    private static MockServer server = null;

    /**
     * Private конструктор, чтобы никто по ошибке не создал дополнительный instance сервера
     */
    private MockServer() {
    }

    /**
     * @return instance реализации сервера
     */
    static MockServer getInstance() {
        if (server == null) {
            server = new MockServer();
        }
        return server;
    }

    @Override
    public double divideBy2(double input) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Log.e(MockServer.class.getSimpleName(), e.getMessage());
        }
        return input / 2;
    }

    /**
     * Метод для установки времени задержки
     * @param sleepTime новое время задержки
     */
    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }
}
