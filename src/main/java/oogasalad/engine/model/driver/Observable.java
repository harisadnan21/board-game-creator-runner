package oogasalad.engine.model.driver;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;


/**
 * Example of managing listeners so other classes do not have to do it themselves. This code was
 * shown to us in class that we can use to make an obervable class. In this case, we make an
 * observable for Boards to implement listeners. Git commit links:
 *
 * @author Haris Adnan
 */
public class Observable<T> {

  private List<PropertyChangeListener> myListeners;

  /**
   * Constructor for the Observable Class
   */
  public Observable() {
    myListeners = new ArrayList<>();
  }

  /**
   * Method adds Listener to the list of listeners.
   *
   * @param listener : listener to be added
   */
  public void addListener(PropertyChangeListener listener) {
    if (listener != null) {
      myListeners.add(listener);
    }
  }

  // notifies listeners by updating the property.
  protected void notifyListeners(String property, T oldValue, T newValue) {
    for (PropertyChangeListener l : myListeners) {
      l.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
    }
  }
}
