package com.sdz.observer;

import com.sdz.model.TModel;

public interface Observable {
  public void addObserver(Observer obs);
  public void removeObserver();
  public void notifyObserver(TModel tableau);
}