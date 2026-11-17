/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.bk.digitalwatch;

import java.util.EventObject;

/**
 *
 * @author marc
 */
public class AlarmEvent extends EventObject{
    private String message;
    
    public AlarmEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
}
