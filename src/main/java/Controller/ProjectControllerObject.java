package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Project;
import Utilities.ConnectionSQL;

/**
 *
 * @author Bruce Mota
 */

/**
 * Metodo que controlar� conex�es entre app e banco de dados, 
 * bem como criar�, atualizar� e deletar� Tarefas
*/
public class ProjectControllerObject {
    //parametro para salvar projeto novo
    public void save(Project project) {
        String sql = "INSERT INTO "
                + "project(name, description, createdAt, updatedAt) "
                + "VALUES (?, ?, ?, ?)";
        //estabelece que n�o h� conex�o pr�viamente, evitando conflitos com anteriores
        Connection conn = null;
        PreparedStatement stmt = null;
        //estabele conex�o com banco de dados e seta valores para nova chave
        try {
            conn = ConnectionSQL.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, project.getName());
            stmt.setString(2, project.getDescription());
            stmt.setDate(3, new java.sql.Date(project.getCreatedAt().getTime()));
            stmt.setDate(4, new java.sql.Date(project.getUpdatedAt().getTime()));
            
            stmt.execute();
        //para evitar desorienta��o, mostrar mensagem indicando que n�o houve conex�o, nem que salvou Tarefa   
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar salvar Tarefa", ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar connex�o com banco de dados", ex);
            }
        }
    }
    //parametro para atualizar Tarefa
    public void update(Project project) {

        String sql = "UPDATE project "
                + "SET name = ?, description = ?, createdAt = ?, updatedAt = ? "
                + "WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionSQL.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, project.getName());
            stmt.setString(2, project.getDescription());
            stmt.setDate(3, new java.sql.Date(project.getCreatedAt().getTime()));
            stmt.setDate(4, new java.sql.Date(project.getUpdatedAt().getTime()));
            stmt.setInt(4, project.getId());
            
            stmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar atualizar Tarefa", ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar connex�o com banco de dados", ex);
            }
        }
    }
    //parametro para deletar Tarefa
    public void removeById(int id) {
            
        String sql = "DELETE FROM project WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionSQL.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar deletar Tarefa", ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar connex�o com banco de dados", ex);
            }
        }
    }
    // parametro que estabelece estrutura no banco de dados e ent�o salvar dados 
    public List<Project> getAll() {
        
        String sql = "SELECT * FROM project";
        
        List<Project> projects = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        ResultSet resultset = null;
        
        try {
            conn = ConnectionSQL.getConnection();
            stmt = conn.prepareStatement(sql);
            
            resultset = stmt.executeQuery();
            
            while (resultset.next()) {
                Project project = new Project();
                
                project.setId(resultset.getInt("id"));
                project.setName(resultset.getString("name"));
                project.setDescription(resultset.getString("description"));
                project.setCreatedAt(resultset.getDate("createdAt"));
                project.setUpdatedAt(resultset.getDate("updatedAt"));
                
                projects.add(project);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar buscar por Tarefas", ex);
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex){
                throw new RuntimeException("Erro ao tentar fechar conex�o com banco de dados", ex);
            }
        }
       return projects;
    }
}
