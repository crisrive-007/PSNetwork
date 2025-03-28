/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psnetwork;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author river
 */
public class PSNUsers {

    private RandomAccessFile archivo, trofeos;
    private HashTable users;

    public PSNUsers() throws IOException {
        archivo = new RandomAccessFile("usuarios.psn", "rw");
        users = new HashTable();
        reloadHashTable();
    }

    private void reloadHashTable() throws IOException {
        archivo.seek(0);
        while (archivo.getFilePointer() < archivo.length()) {
            String username = archivo.readUTF();
            long posicion = archivo.readLong();
            boolean activo = archivo.readBoolean();
            int puntos = archivo.readInt();
            int trofeos = archivo.readInt();
            users.add(username, posicion);
        }
    }

    public void addUser(String username) throws IOException {
        archivo.seek(archivo.length());
        long posicion = archivo.getFilePointer();
        if (usuarioNoExiste(username)) {
            archivo.writeUTF(username);
            archivo.writeLong(posicion);
            archivo.writeBoolean(true);
            archivo.writeInt(0);
            archivo.writeInt(0);
            trofeos = new RandomAccessFile(username + "_trofeos.psn", "rw");
            users.add(username, posicion);
            JOptionPane.showMessageDialog(null, "Se ha creado el usuario.");
        }
        JOptionPane.showMessageDialog(null, "Este usuario ya existe.");
    }

    private boolean usuarioNoExiste(String username) throws IOException {
        archivo.seek(0);
        while (archivo.getFilePointer() < archivo.length()) {
            String nombre = archivo.readUTF();
            long posicion = archivo.readLong();
            boolean activo = archivo.readBoolean();
            int puntos = archivo.readInt();
            int trofeos = archivo.readInt();
            if (nombre.equals(username)) {
                return false;
            }
        }
        return true;
    }

    public void desactivateUser(String username) throws IOException {
        archivo.seek(0);
        while (archivo.getFilePointer() < archivo.length()) {
            String nombre = archivo.readUTF();
            long posicion = archivo.readLong();
            boolean activo = archivo.readBoolean();
            int puntos = archivo.readInt();
            int trofeos = archivo.readInt();
            if (nombre.equals(username)) {
                users.remove(username);
                System.out.println("se borro.");
            }
        }
    }

    public void addTrophieTo(String username, String trophyGame, String trophyName, Trophy type) throws IOException {
        long pos = users.search(username);
        if (pos == -1) {
            JOptionPane.showMessageDialog(null, "Este usuario no existe.");
            return;
        }
        trofeos.seek(trofeos.length());
        trofeos.writeUTF(username);
        trofeos.writeUTF(type.name());
        trofeos.writeUTF(trophyGame);
        trofeos.writeUTF(trophyName);
        trofeos.writeLong(Calendar.getInstance().getTimeInMillis());
    }

    public void playerInfo(String username) throws IOException {
        long pos = users.search(username);
        if (pos == -1) {
            JOptionPane.showMessageDialog(null, "Este usuario no existe.");
            return;
        }
        archivo.seek(pos);
        String usuario = archivo.readUTF();
        archivo.skipBytes(8);
        boolean activo = archivo.readBoolean();
        int puntos = archivo.readInt();
        int trofeos = archivo.readInt();
        archivo.close();

        JOptionPane.showMessageDialog(null, "Usuario: " + usuario + "\nActivo: " + activo + "\nPuntos: " + puntos + "Trofeos: " + trofeos);
        JOptionPane.showMessageDialog(null, "TROFEOS: \n" + infoTrofeos(username));
    }

    private String infoTrofeos(String username) throws IOException {
        trofeos.seek(0);
        String info = "";
        while (trofeos.getFilePointer() < trofeos.length()) {
            String user = trofeos.readUTF();
            String tipo = trofeos.readUTF();
            String juego = trofeos.readUTF();
            String trofeo = trofeos.readUTF();
            long fecha = trofeos.readLong();
            if(user.equals(username)) {
                info += "Fecha: " + new Date(fecha) + "\nTipo: " + tipo + "\nJuego: " + juego + "\nTrofeo: " + trofeo;
            }
        }
        return info;
    }
}
