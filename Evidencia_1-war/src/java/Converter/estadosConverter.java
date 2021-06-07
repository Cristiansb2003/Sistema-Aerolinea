/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Controller.EstadoController;
import Entity.Estados;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Cristian
 */
@FacesConverter(forClass = Estados.class)
public class estadosConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        EstadoController estadoController = context.getApplication().evaluateExpressionGet(context, "#{estadoController}", EstadoController.class);
        return estadoController.find(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Estados estados = (Estados) value;
        return estados.getId().toString();
    }

}
