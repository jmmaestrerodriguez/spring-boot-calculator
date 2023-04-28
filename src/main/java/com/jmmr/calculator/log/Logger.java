package com.jmmr.calculator.log;

import io.corp.calculator.TracerImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Logger implements ILogger {
    private TracerImpl tracer = new TracerImpl();

    @Override
    public void log(String logMessage) {
        this.getTracer().trace(logMessage);
    }

    private TracerImpl getTracer() {
        return this.tracer;
    }
}

