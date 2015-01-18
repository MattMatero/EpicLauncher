package me.matero.epiclauncher;

import android.graphics.drawable.Drawable;

public class AppDetail {
	private CharSequence label;
	private CharSequence name;
	private Drawable icon;
	
	public void setLabel(CharSequence label) {
		this.label = label;
	}
	public CharSequence getLabel() {
		return label;
	}
	public CharSequence getName() {
		return name;
	}
	public Drawable getIcon() {
		return icon;
	}
	public void setName(CharSequence name) {
		this.name = name;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	
	
}
