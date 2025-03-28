/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package psnetwork;

/**
 *
 * @author river
 */
public enum Trophy {
    PLATINO(5), ORO(3), PLATA(2), BRONCE(1);
    
    public final int points;
    
    private Trophy(int points) {
        this.points = points;
    }
}
