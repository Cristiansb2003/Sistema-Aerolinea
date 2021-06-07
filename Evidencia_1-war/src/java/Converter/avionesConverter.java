package Converter;

import Entity.Aviones;
import Controller.AvionesController;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Aviones.class)
public class avionesConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        AvionesController avionesController = context.getApplication().evaluateExpressionGet(context, "#{avionesController}", AvionesController.class);
        return avionesController.find(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Aviones aviones = (Aviones) value;
        return aviones.getId().toString();
    }

}
