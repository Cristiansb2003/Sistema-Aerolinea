package Entity;

import Entity.Aviones;
import Entity.Ciudades;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-06-07T08:51:43", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Vuelos.class)
public class Vuelos_ { 

    public static volatile SingularAttribute<Vuelos, Date> horaFin;
    public static volatile SingularAttribute<Vuelos, Integer> numeroPasajeros;
    public static volatile SingularAttribute<Vuelos, Date> fechaInicio;
    public static volatile SingularAttribute<Vuelos, Long> id;
    public static volatile SingularAttribute<Vuelos, Aviones> numeroAvionV;
    public static volatile SingularAttribute<Vuelos, Ciudades> origen;
    public static volatile SingularAttribute<Vuelos, Ciudades> destino;
    public static volatile SingularAttribute<Vuelos, String> numeroVuelos;
    public static volatile SingularAttribute<Vuelos, Date> fechaFin;
    public static volatile SingularAttribute<Vuelos, Date> horaInicio;

}