package test.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

public class FeedEvent extends ApplicationEvent {
    private String message;
    private ApplicationContext context;

    public ApplicationContext getContext() {
        return context;
    }

    public FeedEvent(Object source, String message, ApplicationContext context) {
        super(source);
        this.message = message;
        this.context = context;
    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
