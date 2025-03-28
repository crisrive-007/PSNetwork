/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psnetwork;

import javax.swing.JOptionPane;

/**
 *
 * @author river
 */
public class HashTable {

    private Entry cabeza;
    private Entry cola;

    public void add(String username, long pos) {
        Entry nuevo = new Entry(username, pos);
        if (cola == null) {
            cabeza = nuevo;
            cola = nuevo;
        }
        cola.next = nuevo;
        cola = cola.next;
    }

    public void remove(String username) {
        Entry actual = cabeza;
        if (actual == null) {
            JOptionPane.showMessageDialog(null, "Este usuario no existe.");
        } else {
            while (actual != cola) {
                if (actual.username.equals(username)) {
                    if (eliminarCuenta() == 0) {
                        JOptionPane.showMessageDialog(null, "Cuenta eliminada exitosamente.");
                    }
                }
            }
        }
    }

    private int eliminarCuenta() {
        int eliminar = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la cuenta?");
        return eliminar;
    }
    
    public long search(String username) {
        Entry actual = cabeza;
        if (actual == null) {
            JOptionPane.showMessageDialog(null, "Este usuario no existe.");
        } else {
            while (actual != cola) {
                if (actual.username.equals(username)) {
                    return actual.posicion;
                }
            }
        }
        return -1;
    }
}
