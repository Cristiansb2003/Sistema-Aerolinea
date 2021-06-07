package Entity;

import Entity.Ciudades;
import Entity.Paises;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-06-07T08:51:43", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Estados.class)
public class Estados_ { 

    public static volatile SingularAttribute<Estados, Long> id;
    public static volatile ListAttribute<Estados, Ciudades> ciudadesList;
    public static volatile SingularAttribute<Estados, String> nombre;
    public static volatile SingularAttribute<Estados, Paises> pais;

}