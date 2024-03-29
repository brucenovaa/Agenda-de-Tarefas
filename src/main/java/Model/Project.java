package Model;

import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author Bruce Mota
 */

/**
 * 
 * Metodo que estabelece gets e sets de cada info do banco de dados
 */
public class Project {
    
    private int id;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private static final Logger LOG = Logger.getLogger(Project.class.getName());

    public Project(int id, String name, String description, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public Project() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Project{" + "name=" + name + '}';
    }


   
}
