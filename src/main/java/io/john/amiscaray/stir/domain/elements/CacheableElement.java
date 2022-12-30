package io.john.amiscaray.stir.domain.elements;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class CacheableElement {

    protected final PropertyChangeSupport propertyChangeSupport;

    protected CacheStatus cacheStatus = CacheStatus.EMPTY;

    private String cacheContents;

    private boolean hasChildren = false;

    protected boolean cacheDisabled;

    public CacheStatus getCacheStatus() {
        return this.cacheStatus;
    }

    public String getCacheContents() {
        return this.cacheContents;
    }

    public boolean isHasChildren() {
        return this.hasChildren;
    }

    public boolean isCacheDisabled() {
        return this.cacheDisabled;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public void setCacheDisabled(boolean cacheDisabled) {
        this.cacheDisabled = cacheDisabled;
    }

    public enum CacheStatus {
        EMPTY,
        DIRTY,
        CLEAN
    }

    public CacheableElement(){
        propertyChangeSupport = new PropertyChangeSupport(this);
        propertyChangeSupport.addPropertyChangeListener(e -> {
            if(cacheStatus.equals(CacheStatus.EMPTY)){
                return;
            }
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
