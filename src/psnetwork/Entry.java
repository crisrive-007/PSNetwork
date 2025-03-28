/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psnetwork;

/**
 *
 * @author river
 */
public class Entry {
    public String username;
    public long posicion;
    public Entry next;
    
    public Entry(String username, long posicion) {
        this.username = username;
        this.posicion = posicion;
        this.next = null;
    }
}
