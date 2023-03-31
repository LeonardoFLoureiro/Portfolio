package screen;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.IlhaMODEL;
import service.IlhaSERVICE;

public class ScreenATRIBUTOS extends JFrame{
    public ScreenATRIBUTOS(){
        this.setTitle("Atributos");
        this.setLayout(null);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        this.setResizable(true);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.black);
        this.setVisible(true);

        
    }
    
}
