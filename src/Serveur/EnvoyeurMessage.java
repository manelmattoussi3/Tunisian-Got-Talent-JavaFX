/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serveur;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class EnvoyeurMessage {

    private DataOutputStream envoyeur;

    public EnvoyeurMessage(OutputStream envoyeur) {
        this.envoyeur = new DataOutputStream(envoyeur);

    }

    public void envoyer(String messageVeutEnvoyer) throws IOException {
        envoyeur.writeInt(messageVeutEnvoyer.length());
        envoyeur.write(messageVeutEnvoyer.getBytes());
        envoyeur.flush();

    }

}
