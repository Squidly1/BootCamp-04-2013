package de.wombatsoftware.TweetCamp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.inject.Named;

@SessionScoped
@Named
public class LocaleHandler implements Serializable, ValueChangeListener {

	private static final long serialVersionUID = 6264779693821747650L;
	private String locale;
	
	@PostConstruct
	public void init(){
		locale = FacesContext.getCurrentInstance().getViewRoot().getLocale().toString();
	}
	
	public List<String> getSupportedLocales(){
		List<String> supportedLocales = new ArrayList<String>();
		
		Iterator<Locale> iter =  FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
		
		while(iter.hasNext()){
			supportedLocales.add(iter.next().toString());
		}
		
		return supportedLocales;
	}
	
	public void changeLocale(){
		
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(locale));
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@Override
	public void processValueChange(ValueChangeEvent arg0) throws AbortProcessingException {
		this.locale = arg0.getNewValue().toString();
		changeLocale();
	}
	
}
