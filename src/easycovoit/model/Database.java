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

    public ResultSet seConnecter(String email,String motDePasse) throws SQLException {
            PreparedStatement statement = this.cn.prepareStatement("SELECT * FROM Utilisateur WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, motDePasse);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                    return rs;
            } else {
                    return null;
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

    public ResultSet getAllTrajet(String VilleDepart,String VilleArrive,Timestamp dateDepart){
        try {
            PreparedStatement statement = this.cn.prepareStatement(
                    "SELECT * FROM Trajet WHERE idTrajet = (SELECT idTrajet FROM VilleEtape as VilleEtape1 WHERE" +
                    " ville = ? AND datePassage BETWEEN ? AND DATE_ADD(?,INTERVAL 1 HOUR) AND " +
                            "idTrajet IN (SELECT idTrajet FROM VilleEtape as VilleEtape2" +
                    " WHERE ville = ? AND VilleEtape1.datePassage < VilleEtape2.datePassage))");
            statement.setString(1,VilleDepart);
            statement.setTimestamp(2,dateDepart);
            statement.setTimestamp(3,dateDepart);
            statement.setString(4,VilleArrive);
            System.out.println(statement);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public ResultSet getMyTrajet(int idUser){
        try {
            PreparedStatement statement = this.cn.prepareStatement("SELECT * FROM Trajet " +
                    "INNER JOIN VilleEtape ON Trajet.idTrajet = VilleEtape.idTrajet WHERE Trajet.idConducteur = ?");
            statement.setInt(1,idUser);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

