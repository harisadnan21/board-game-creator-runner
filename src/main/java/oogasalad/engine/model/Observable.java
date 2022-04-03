package oogasalad.engine.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

//Observable Class by Robert Duvall
/**
 * Example of managing listeners so other classes do not have to do it themselves.
 *
 * Note, this is the heart of an interactive system: notify all abstractions that have registered interest.
 *
 * This level is generic even though the bean classes it uses are not (they pass around simple Object classes).
 *
 * @author Robert C. Duvall
 */
public class Observable<T> {
  private List<PropertyChangeListener> myListeners;

  public Observable () {
    myListeners = new ArrayList<>();
  }

  public void addListener (PropertyChangeListener listener) {
    if (listener != null) {
      myListeners.add(listener);
    }
    // FIXME: throw error to report or just ignore?
  }

  // control access to only allow subclasses to call it
  protected void notifyListeners (String property, T oldValue, T newValue) {
    for (PropertyChangeListener l : myListeners) {
      l.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
    }
  }
}

