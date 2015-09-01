package jaas;

import java.io.Serializable;
import java.security.Principal;

/**
 * Holds the logged in users name
 *
 * @author sixthpoint
 */
public class UserPrincipal implements Principal, Serializable{

	private static final long serialVersionUID = 7515166994229329836L;
	
	private String name;

    /**
     * Initializer
     *
     * @param name
     */
    public UserPrincipal(String name) {
        super();
        this.name = name;
    }

    /**
     * Set the name of the user
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of the user
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
        UserPrincipal other = (UserPrincipal) obj;
        if (name == null) {
           if (other.name != null)
              return false;
        } else if (!name.equals(other.name))
           return false;
     
        return true;
    }
}