/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easycovoit.model;

import java.sql.*;

public class Database {
    
    private static final String url = "jdbc:mysql://89.234.180.28/wijdei_covoit";
    private static final String username = "wijdei_covoit";
    private static final String password = "covoit";

    private java.sql.Connection cn = null;
    private Statement st = null;

    public Database() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.jdbc.Driver");
            this.cn = DriverManager.getConnection(url, username, password);
    }

    public java.sql.Statement getNewStatement() throws SQLException {
            return cn.createStatement();
    }

    public void setCn(java.sql.Connection cn) {
            this.cn = cn;
    }

    public Statement getSt() {
            return st;
    }

    public void setSt(Statement st) {
            this.st = st;
    }

    public boolean seConnecter(String email,String motDePasse) throws SQLException {
            PreparedStatement statement = this.cn.prepareStatement("SELECT * FROM Utilisateur WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, motDePasse);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                    return true;
            } else {
                    return false;
            }
    }

    public boolean insererUtilisateur(Utilisateur user) throws SQLException {
            PreparedStatement statement = this.cn.prepareStatement("INSERT INTO Utilisateur(email,password,numTel) VALUES(?,?,?)");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getMotDePasse());
            statement.setString(3, user.getTelephone());
            statement.executeUpdate();
            return true;
    }
}

