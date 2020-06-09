/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author DevelopAndroid
 */
public class ManageurClient extends Thread {

    private final Socket manager;
    private final String text;

    public ManageurClient(Socket manager, String text) {
        this.manager = manager;
        this.text = text;
    }
@Override
    public void run() {
        OutputStream os;
        try {
            os=manager.getOutputStream();
            byte[] bites=text.getBytes();
            os.write(bites);
            os.flush();
            os.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
