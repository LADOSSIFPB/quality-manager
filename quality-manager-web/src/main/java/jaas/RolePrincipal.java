package jaas;

import java.io.Serializable;
import java.security.Principal;

/**
 * Holds a single role name that the user is in
 *
 * @author sixthpoint
 */
public class RolePrincipal implements Principal, Serializable {

	private static final long serialVersionUID = -5962221796529461110L;
	
	private String name;

    /**
     * Initializer
     *
     * @param name
     */
    public RolePrincipal(String name) {
        super();
        this.name = name;
    }

    /**
     * Set the role name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the role name
     *
     * @return
     */
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
           return true;
        if (obj == null)
           return false;
        if (getClass() != obj.getClass())
           return false;
        RolePrincipal other = (RolePrincipal) obj;
        if (name == null) {
           if (other.name != null)
              return false;
        } else if (!name.equals(other.name))
           return false;
        
        return true;
    }
}