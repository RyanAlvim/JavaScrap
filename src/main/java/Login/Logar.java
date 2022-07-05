package Login;

import javax.swing.*;

import Categorias.Categorias;
import ZapScrap.DadosProdutos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Logar extends JFrame implements ActionListener {

	public static String email;
	public static String senha;
	
    Container container = getContentPane();
    JLabel userLabel = new JLabel("Email");
    JLabel passwordLabel = new JLabel("Senha");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGAR");
    JButton resetButton = new JButton("Resetar Dados");
    JCheckBox showPassword = new JCheckBox("Mostrar Senha");


    public Logar() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            email = userTextField.getText();
            senha = passwordField.getText();
            Categorias categorias = new Categorias();
            try {
				categorias.Category();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
            
        }

        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }
    }

}

