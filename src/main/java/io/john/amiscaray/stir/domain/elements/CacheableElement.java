package io.john.amiscaray.stir.domain.elements;

import lombok.Getter;
import lombok.Setter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class CacheableElement {

    protected final PropertyChangeSupport propertyChangeSupport;

    @Getter
    protected CacheStatus cacheStatus = CacheStatus.EMPTY;

    @Getter
    private String cacheContents;

    @Getter
    @Setter
    private boolean hasChildren = false;

    @Getter
    @Setter
    protected boolean cacheDisabled;

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
