package br.com.lasbr.erp.controller;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.lasbr.erp.model.FieldActivity;

@SuppressWarnings("rawtypes")
public class FieldActivityConveter implements Converter {
	
	private List<FieldActivity> listFieldAcitivity;
	

	public FieldActivityConveter(List<FieldActivity> listFieldAcitivity) {
		this.listFieldAcitivity = listFieldAcitivity;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		
		Integer id = Integer.valueOf(value);
		for (FieldActivity fieldActivity: listFieldAcitivity) {
			if (id.equals(fieldActivity.getId())) {
				return fieldActivity;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		
		FieldActivity fieldActivity = (FieldActivity) value;
		return fieldActivity.getId().toString();
	}
}
