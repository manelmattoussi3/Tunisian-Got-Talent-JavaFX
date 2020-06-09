package Serveur;

//import Serveur.RecepteurMessage;
//import Serveur.EnvoyeurMessage;

public interface FrameInterface {

    void recoieMessage(String message);

    void setEnvoyeur(EnvoyeurMessage e);

    void setRecepteur(RecepteurMessage r);

}
