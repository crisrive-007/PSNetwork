/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psnetwork;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author river
 */
public class PSNUsers {
    private RandomAccessFile archivo;
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
        }
        users.add(username, posicion);
    }
    
    private boolean usuarioNoExiste(String username) throws IOException {
        archivo.seek(0);
        while (archivo.getFilePointer() < archivo.length()) {
            String nombre = archivo.readUTF();
            archivo.readUTF();
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
            if (nombre.equals(username)) {
                users.remove(username);
            }
        }
    }
    
    public void addTrophieTo(String username, String trophyGame, String trophyName, Trophy type) {
        
    }
}
