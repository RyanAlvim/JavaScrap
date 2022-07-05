package Main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

import Categorias.Categorias;
import Config.Configs;
import Login.Logar;

public class Main {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		FileWriter file = new FileWriter(Configs.produtos);
		file.flush();
		
		Logar frame = new Logar();
		frame.setTitle("Zap Gráfica");
		frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

	}
}
