package screen;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.IlhaMODEL;
import service.IlhaSERVICE;

public class ScreenHOME extends JFrame implements MouseListener, ActionListener, MouseMotionListener {
    boolean ISOpeningNewScreen;
    // Imagens
    ImageIcon BackgroundImage;
    ImageIcon icon;
    // Elementos da tela
    JLabel pins[];
    JLayeredPane layeredpane;
    JButton btnaddIlha;
    JButton btnrmvIlha;

    JLabel Background;
    JLabel texto;
    // Acesso ao BD
    IlhaSERVICE service;
    IlhaMODEL Ilhas[];
    // Booleans de ativação
    boolean addIlha;
    boolean rmvilha;
    boolean mvrilha;
    boolean clickmvrilha;
    int mvrnum;
    // Controlador do Número de pins
    int nPins;
    JMenu mapa;
    JMenuItem mapaPrincipal;
    JMenu personagens;
    JMenuItem personagens_atributos;
    JMenuItem personagens_backstory;
    // Construtor da tela
    public ScreenHOME() {
        ISOpeningNewScreen = false;
        service = new IlhaSERVICE(); // Relacionamento com o bd

        // Passagem dos valores do bd para memoria primaria
        Ilhas = new IlhaMODEL[1000];
        IlhaMODEL tmp[] = service.getall();
        if (tmp == null) {
            service.add(new IlhaMODEL("Ilha", "descricao", 100, 100, "icon"));
        }
        tmp = service.getall();
        for (int i = 0; i < tmp.length; i++) {
            Ilhas[i] = tmp[i];
        }
        // Ativação das variáveis de ativação
        addIlha = false;
        rmvilha = false;
        mvrilha = false;
        clickmvrilha = false;

        // Construtores do JFrame
        this.setTitle("Home");
        this.setLayout(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1250, 870);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.black);

        // Construtores do layeredPane
        layeredpane = new JLayeredPane();
        layeredpane.setBounds(0, 0, 1250, 870);

        // Definição das imagens e dimensionamento
        icon = new ImageIcon("src/main/resources/public/imgs/pin.png");
        icon.setImage(icon.getImage().getScaledInstance(30, 30, 1));

        ImageIcon imgnavio = new ImageIcon("src/main/resources/public/imgs/Ship.png");
        imgnavio.setImage(imgnavio.getImage().getScaledInstance(50, 50, 1));

        BackgroundImage = new ImageIcon("src/main/resources/public/imgs/Mapa.jpg");
        BackgroundImage.setImage(BackgroundImage.getImage().getScaledInstance(1090, 790, 1));

        // Construtores do Label de BackGround
        Background = new JLabel();
        Background.setBackground(Color.black);
        Background.setIcon(BackgroundImage);
        Background.setBounds(10, 10, 1090, 790);
        Background.setOpaque(true);
        Background.addMouseListener(this);

        // Construtores dos Labels de pin
        pins = new JLabel[1000];
        for (nPins = 0; nPins < tmp.length; nPins++) {
            pins[nPins] = new JLabel();
            pins[nPins].setIcon(icon);
            pins[nPins].setBounds(Ilhas[nPins].getX(), Ilhas[nPins].getY() - 25, 30, 30);
            pins[nPins].setOpaque(false);
            pins[nPins].addMouseListener(this);
            pins[nPins].addMouseMotionListener(this);
            pins[nPins].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            layeredpane.add(pins[nPins]); // Inclusão dos pins ao layeredPane
        }

        // Construtores do Botão de Adicionar Ilhas
        btnaddIlha = new JButton("+");
        btnaddIlha.setIcon(icon);
        btnaddIlha.setBackground(Color.gray);
        btnaddIlha.setFocusable(false);
        btnaddIlha.setHorizontalTextPosition(btnaddIlha.LEFT);
        btnaddIlha.setBorder(BorderFactory.createEtchedBorder());
        btnaddIlha.addActionListener(this);
        btnaddIlha.setBounds(1150, 50, 60, 60);
        btnaddIlha.setFont(new Font("Arial", Font.BOLD, 20));

        // Construtores do Botão de Remover Ilhas
        btnrmvIlha = new JButton("-");
        btnrmvIlha.setIcon(icon);
        btnrmvIlha.setBackground(Color.gray);
        btnrmvIlha.setFocusable(false);
        btnrmvIlha.setHorizontalTextPosition(btnaddIlha.LEFT);
        btnrmvIlha.setBorder(BorderFactory.createEtchedBorder());
        btnrmvIlha.addActionListener(this);
        btnrmvIlha.setBounds(1150, 130, 60, 60);
        btnrmvIlha.setFont(new Font("Arial", Font.BOLD, 20));
        //
        JLabel barco = new JLabel();
        barco = new JLabel();
        barco.setIcon(imgnavio);
        barco.setBounds(100, 500, 50, 50);
        barco.setOpaque(false);
        barco.addMouseListener(this);
        barco.addMouseMotionListener(this);
        barco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // layeredpane.add(barco);
        // Menubar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.BLACK);
        
        mapa = new JMenu("Mapa");
        mapa.setForeground(Color.white);
        personagens = new JMenu("Personagens");
        personagens.setForeground(Color.white);

        personagens_atributos = new JMenuItem("Atributos / Ficha");
        personagens_backstory = new JMenuItem("História");

        mapaPrincipal = new JMenuItem("Mapa Principal");
        mapaPrincipal.addActionListener(this);
        
        mapa.add(mapaPrincipal);
        personagens.add(personagens_atributos);
        personagens_atributos.addActionListener(this);
        personagens.add(personagens_backstory);
        personagens_backstory.addActionListener(this);
        
        menuBar.add(mapa);
        menuBar.add(personagens);
        // Adições dos componentes ao layeredPane
        layeredpane.add(Background);
        layeredpane.add(btnaddIlha);
        layeredpane.add(btnrmvIlha);
        
        this.setJMenuBar(menuBar);
        this.add(layeredpane); // Adição do layeredPane ao JFrame

        this.setVisible(true); // Visibildade do JFrame ativada
    }

    // Métodos para efeitos de MouseListener
    
    public void mouseClicked(java.awt.event.MouseEvent e) {

        // Função para adicionar nova ilha
        if (e.getSource() == Background && addIlha) {
            addIlha = false; // Redefinição da váriavel de ativação

            // Adição ao banco de dados
            IlhaMODEL novaILHA = new IlhaMODEL("Nova nota", "Descrição da ilha", e.getX() + Background.getX(),
                    e.getY() + Background.getY(), "icon");
            service.add(novaILHA);

            // Adição a tela
            novoPin(novaILHA);

            // Remontagem da tela
            layeredpane.remove(Background);
            for (int i = 0; i < nPins - 1; i++) {
                layeredpane.remove(pins[i]);
            }
            for (int i = 0; i < nPins; i++) {
                layeredpane.add(pins[i]);
            }
            layeredpane.add(Background);
            this.add(layeredpane);
            this.setVisible(true);

        } else if (e.getSource() == Background) {

        }
        // Função para remover uma ilha
        else if (rmvilha) {
            int posComponente = numComponente(e.getComponent(), 0);// Retorna a posição do componente

            // JOptionPane para confirmação da ação
            if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir essa ilha?") == 0) {
                service.remove(Ilhas[posComponente]);
                layeredpane.remove(pins[posComponente]);
                layeredpane.remove(texto);
                reorganizarPins(posComponente);
                nPins--;
                this.setVisible(true);
            }

            rmvilha = false; // Redefinição da variavel de ativação

        } else {
            // Ação de ativação do JFrame da ilha selecionada
            int posComponente = numComponente(e.getComponent(), 0);
            ISOpeningNewScreen = true;
            new ScreenILHA(Ilhas[posComponente]);

        }

    }

    
    public void mousePressed(java.awt.event.MouseEvent e) {

        if (e.getSource() != Background) {
            mvrnum = numComponente(e.getComponent(), 0);
            mvrilha = true;
        }

    }

   
    public void mouseReleased(java.awt.event.MouseEvent e) {

        if (e.getSource() != Background && clickmvrilha) {
            pins[mvrnum].setVisible(true);

            service.update(Ilhas[mvrnum]);

            pins[mvrnum].setBounds(Ilhas[mvrnum].getX(), Ilhas[mvrnum].getY() - 25, 30, 30);
            layeredpane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            // Remontagem da tela
            layeredpane.remove(Background);
            for (int i = 0; i < nPins; i++) {
                layeredpane.remove(pins[i]);
            }
            for (int i = 0; i < nPins; i++) {
                layeredpane.add(pins[i]);
            }
            layeredpane.add(Background);
            this.add(layeredpane);
            this.setVisible(true);
            clickmvrilha = false;
        }
    }

    
    public void mouseEntered(java.awt.event.MouseEvent e) {

        if (e.getSource() == Background) {

        } else { // Ação executada se o componente for um pin
            int posComponente = numComponente(e.getComponent(), 0);// Ação para pegar valor da pos do pin no vetor

            // Animação Gráfica do pin
            pins[posComponente].setBounds(pins[posComponente].getX(), pins[posComponente].getY() - 30, 60, 60);
            ImageIcon newicon = new ImageIcon(icon.getImage().getScaledInstance(60, 60, 1));
            pins[posComponente].setIcon(newicon);

            // Construtores do Label do nome da ilha
            texto = new JLabel(Ilhas[posComponente].getNome());
            texto.setBounds(pins[posComponente].getX() + 60, pins[posComponente].getY(), 500, 35);
            texto.setOpaque(false);
            texto.setFont(new Font("Algerian", Font.BOLD, 35));

            // Remontagem da tela
            layeredpane.remove(pins[posComponente]);
            layeredpane.remove(Background);
            layeredpane.add(texto);
            layeredpane.add(pins[posComponente]);
            layeredpane.add(Background);
            this.add(layeredpane);
            this.setVisible(true);
        }
    }

    
    public void mouseExited(java.awt.event.MouseEvent e) {
        if (e.getSource() == Background) {

        } else { // Ação executada se o componente for um pin
            int posComponente = numComponente(e.getComponent(), 0);// Ação para pegar valor da pos do pin no vetor

            // Retorno da animação executada na func mouseEntered
            pins[posComponente].setBounds(pins[posComponente].getX(), pins[posComponente].getY() + 30, 30, 30);
            pins[posComponente].setIcon(icon);
            texto.setVisible(false);// Ação para apagar texto para o usuario
            if (!ISOpeningNewScreen) {
                this.setVisible(true);
            }
            ISOpeningNewScreen = false;

        }
    }

   
    public void mouseDragged(java.awt.event.MouseEvent e) {
        if (mvrilha == true) {
            System.out.println("selecionada");

            Ilhas[mvrnum].setX(pins[mvrnum].getX() + e.getX());
            Ilhas[mvrnum].setY(pins[mvrnum].getY() + e.getY() + 25);
            System.out.println("POS = " + Ilhas[mvrnum].getX() + " " + Ilhas[mvrnum].getY());

            pins[mvrnum].setVisible(false);
            clickmvrilha = true;
            CustomCursor();
        }
    }

    
    public void mouseMoved(java.awt.event.MouseEvent e) {

    }

    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnaddIlha) { // Ativação da habilidade de adicionar ilha
            addIlha = true;

        } else if (e.getSource() == btnrmvIlha) { // Ativação da habilidade de remover ilha
            rmvilha = true;
        }else if(e.getSource() == mapaPrincipal){
            System.out.println("ROGERIO");
        }else if(e.getSource() == personagens_atributos){
            System.out.println("SAMBA");
            new ScreenATRIBUTOS();

        }

    }

    public void novoPin(IlhaMODEL novailha) {
        Ilhas[nPins] = new IlhaMODEL(novailha);
        pins[nPins] = new JLabel();
        pins[nPins].setIcon(icon);
        pins[nPins].setBounds(Ilhas[nPins].getX(), Ilhas[nPins].getY() - 25, 30, 30);
        pins[nPins].setOpaque(false);
        pins[nPins].addMouseListener(this);
        pins[nPins].addMouseMotionListener(this);
        pins[nPins].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nPins++;
    }

    public int numComponente(Component component, int i) {

        if (pins[i] != component) {
            i = numComponente(component, i + 1);
        }
        return i;
    }

    public void CustomCursor() {
        Toolkit tools = Toolkit.getDefaultToolkit();
        Image img = tools.getImage("src/main/resources/public/imgs/pin.png");
        Point point = new Point(0, 0);
        Cursor cursor = tools.createCustomCursor(img, point, "cursor");
        layeredpane.setCursor(cursor);
    }

    public void reorganizarPins(int apagado) {
        for (int i = apagado; pins[i + 1] != null; i++) {
            pins[i] = pins[i + 1];
        }
    }
}
