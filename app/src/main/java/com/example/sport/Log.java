package com.example.sport;

public class Log {
    private Logs log;

    public Log() {
        this.log = log;
    }

    public Logs getLog() {
        return log;
    }

    public void setLog(Logs log) {
        this.log = log;
    }
    public enum Logs {
        EmailVerification,
        LoginFailed,
        LoginSuccess
    }
}
