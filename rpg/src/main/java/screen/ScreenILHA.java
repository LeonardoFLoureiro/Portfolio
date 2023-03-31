package screen;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import dao.IlhaDAO;
import model.IlhaMODEL;
import service.IlhaSERVICE;

public class ScreenILHA extends JFrame implements ActionListener {
    IlhaMODEL ilha;
    JButton botao;
    JTextArea textarea;
    JTextField NomeIlha;
    JScrollPane pane;
    IlhaSERVICE service = new IlhaSERVICE();

    public ScreenILHA(IlhaMODEL ilha) {
        

        this.ilha = ilha;
        this.setTitle("Ilha");
        this.setLayout(null);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(400, 750);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.black);

        ////////////////////////////
        System.out.println();
        textarea = new JTextArea(20,20);
        textarea.setText(ilha.getDescricao());
        textarea.setLineWrap(true);
        textarea.setFont(new Font("Arial",Font.BOLD,14));
        textarea.setBackground(Color.black);
        textarea.setForeground(Color.white);

        ///////////////////////////

        ///////////////////////
        NomeIlha = new JTextField(ilha.getNome());
        NomeIlha.setBounds(50, 20, 300, 80);
        NomeIlha.setBackground(Color.BLACK);
        NomeIlha.setForeground(Color.white);
        NomeIlha.setFont(new Font("Arial", Font.BOLD, 30));
        NomeIlha.setHorizontalAlignment(NomeIlha.CENTER);
        //////////////////////
        pane = new JScrollPane(textarea);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setBounds(50, 150, 300, 400);
        /////////////////////
        botao = new JButton("Salvar");
        botao.setBounds(100, 600, 200, 80);
        botao.addActionListener(this);
        this.add(pane);
        this.add(NomeIlha);
        this.add(botao);
        this.setVisible(true);
    }

    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botao) {
            ilha.setNome(NomeIlha.getText());
            ilha.setDescricao(textarea.getText());
            service.update(ilha);
            JOptionPane.showMessageDialog(null,"Alterações feitas", "Salvo",JOptionPane.INFORMATION_MESSAGE);
            

        }
    }

}
