package io.john.amiscaray.stir.domain.elements;

import lombok.Getter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class CacheableElement {

    protected final PropertyChangeSupport propertyChangeSupport;
    @Getter
    protected CacheStatus cacheStatus = CacheStatus.EMPTY;

    @Getter
    private String cacheContents;

    public enum CacheStatus {
        EMPTY,
        DIRTY,
        CLEAN
    }

    public CacheableElement(){
        propertyChangeSupport = new PropertyChangeSupport(this);
        propertyChangeSupport.addPropertyChangeListener(e -> {
            cacheStatus = CacheStatus.DIRTY;
        });
    }

    public void setCacheContents(String cacheContents) {
        this.cacheContents = cacheContents;
        cacheStatus = CacheStatus.CLEAN;
    }

    public void emptyCache(){
        cacheContents = null;
        cacheStatus = CacheStatus.EMPTY;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

}
