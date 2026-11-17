/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package cat.bk.digitalwatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author marc
 */
public class DigitalWatchBean extends JLabel implements Serializable, ActionListener {
    private boolean isFormat24h = true;
    private boolean isAlarmEnabled = false;
    private int alarmHour = 0;
    private int alarmMinute = 0;
    private String alarmText = "¡S'ha arribat a l'hora especificada!";
    
    private Timer timer;
    private AlarmListener receiver;
    
    public DigitalWatchBean() {
        refreshTime();
        timer = new Timer(1000, this);
        timer.start();
    }

    public boolean isFormat24h() {
        return isFormat24h;
    }

    public void setIsFormat24h(boolean isFormat24h) {
        this.isFormat24h = isFormat24h;
    }

    public boolean isAlarmEnabled() {
        return isAlarmEnabled;
    }

    public void setIsAlarmEnabled(boolean isAlarmEnabled) {
        this.isAlarmEnabled = isAlarmEnabled;
    }

    public int getAlarmHour() {
        return alarmHour;
    }

    public void setAlarmHour(int alarmHour) {
        this.alarmHour = alarmHour;
    }

    public int getAlarmMinute() {
        return alarmMinute;
    }

    public void setAlarmMinute(int alarmMinute) {
        this.alarmMinute = alarmMinute;
    }

    public String getAlarmText() {
        return alarmText;
    }

    public void setAlarmText(String alarmText) {
        this.alarmText = alarmText;
    }
    
    
    private void refreshTime() {
        LocalTime currentTime = LocalTime.now(ZoneId.of("Europe/Madrid"));
        
        DateTimeFormatter format;
        
        if (isFormat24h) {
            format = DateTimeFormatter.ofPattern("HH:mm:ss");
        } else {
            format = DateTimeFormatter.ofPattern("hh:mm:ss a");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        refreshTime();
        
        if (isAlarmEnabled && receiver != null) {
            LocalTime currentTime = LocalTime.now(ZoneId.of("Europe/Madrid"));
            
            if (currentTime.getHour() == alarmHour && currentTime.getMinute() == alarmMinute) {
                receiver.catchAlarm(new AlarmEvent(this, alarmText));
            }
        }
        
        repaint();
    }
    
}
