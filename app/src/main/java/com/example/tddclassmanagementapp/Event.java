package com.example.tddclassmanagementapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

public class Event<T> {
    public interface EventHandler<T> {
        void onEventUnhandledContent(T content);
    }

    private T content;
    private boolean hasBeenHandled;

    public Event(@NonNull T content) {
        this.content = content;
    }

    public T getContentIfNotHandled() {
        if (hasBeenHandled) return null;
        hasBeenHandled = true;
        return content;
    }

    public T peekContent() {
        return content;
    }

    public static class EventObserver<T> implements Observer<Event<T>> {
        private EventHandler<T> handler;

        public EventObserver(EventHandler<T> handler) {
            this.handler = handler;
        }

        @Override
        public void onChanged(Event<T> tEvent) {
            T content = tEvent.getContentIfNotHandled();
            if (content != null) {
                handler.onEventUnhandledContent(content);
            }
        }
    }
}
