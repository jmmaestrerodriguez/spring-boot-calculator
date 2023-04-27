package com.jmmr.calculator.log;

import io.corp.calculator.TracerImpl;
import org.springframework.stereotype.Component;

@Component
public class Logger implements ILogger{
    private TracerImpl tracer;

    @Override
    public void log(String logMessage) {
        this.getTracer().trace(logMessage);
    }

    private TracerImpl getTracer() {
        if (tracer == null) {
            this.tracer = new TracerImpl();
        }
        return this.tracer;
    }

    public void Logger() {
        this.tracer = new TracerImpl();
    }

}
