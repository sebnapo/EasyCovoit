/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easycovoit.model;

/**
 *
 * @author anonymous
 */
public class Utilisateur {
    private String telephone;
    public String getTelephone() {
            return telephone;
    }

    public void setTelephone(String telephone) {
            this.telephone = telephone;
    }

    public String getEmail() {
            return email;
    }

    public void setEmail(String email) {
            this.email = email;
    }

    public String getMotDePasse() {
            return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
            this.motDePasse = motDePasse;
    }

    private String email;
    private String motDePasse;

    public Utilisateur(String email, String motDePasse, String telephone) {
            this.email = email;
            this.motDePasse = motDePasse;
            this.telephone = telephone;
    }
}
