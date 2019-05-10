package code.flatura.teamlunch.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.UUID;


/**
 * Simple JavaBean, Used as a base class for objects needing name property.
 * @author edited by Dmitry Morozov for TeamLunch Graduation Project
 */
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    protected String name;

    protected AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), getId(), this.name);
    }
}

