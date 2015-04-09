package yadierq87.desarrollo.changemyicon;

import android.graphics.drawable.Drawable;

public class Apks {
	
	String name_apk;String info_apk; Drawable icono;
	public Apks(String name_apk, String info_apk, Drawable icono) {
		super();
		this.name_apk = name_apk;
		this.info_apk = info_apk;
		this.icono = icono;
	}
	public String getName_apk() {
		return name_apk;
	}	
	public String getInfo_apk() {
		return info_apk;
	}	
	public Drawable getIcono() {
		return icono;
	}	
}
