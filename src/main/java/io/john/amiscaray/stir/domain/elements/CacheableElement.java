package io.john.amiscaray.stir.domain.elements;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The base class for element cache operations
 */
public abstract class CacheableElement {

    /**
     * Used to listen for object property change events to appropriately update the cache
     */
    protected final PropertyChangeSupport propertyChangeSupport;

    /**
     * Used to keep track of the cache freshness. See the {@link CacheStatus CacheStatus enum}
     */
    protected CacheStatus cacheStatus = CacheStatus.EMPTY;

    /**
     * The cache contents for the element
     */
    private String cacheContents;

    /**
     * Whether the element has children elements (used for caching purposes)
     */
    private boolean hasChildren = false;

    /**
     * Whether to disable caching on the element
     */
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

    /**
     * Adds an event listener for any changes to the object
     * @param listener A {@link PropertyChangeListener PropertyChangeListener} for when an object property changes
     */
    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

}
