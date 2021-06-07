package Converter;

import Entity.Ciudades;
import Controller.CiudadesController;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Ciudades.class)
public class ciudadesConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        CiudadesController ciudadesController = context.getApplication().evaluateExpressionGet(context, "#{ciudadesController}", CiudadesController.class);
        return ciudadesController.find(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Ciudades ciudades = (Ciudades) value;
        return ciudades.getId().toString();
    }

}
