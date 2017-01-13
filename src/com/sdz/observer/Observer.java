package com.sdz.observer;

import javax.swing.JTable;

import com.sdz.model.TModel;

public interface Observer {
	public void update(TModel tableau, int tpAatt);
	
}
