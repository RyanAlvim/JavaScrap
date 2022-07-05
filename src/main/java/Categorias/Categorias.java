package Categorias;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Config.Configs;
import Login.Logar;
import ZapScrap.DadosProdutos;

public class Categorias {
	public static WebDriver driver;
	private static ArrayList<String> linksRepetidos = new ArrayList<String>();
	
	
	public Categorias() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--headless");
		driver = new ChromeDriver(option);
	}
	
	public static void Category() throws IOException, InterruptedException {
		Scanner scan = new Scanner(new File(Configs.categorias));
		FileWriter file = new FileWriter(Configs.produtos,true);

		while(scan.hasNextLine()) {	
			String linha = scan.nextLine();
			driver.get("https://zapgrafica.com.br/loja/produto/categoria/"+linha);
			List<WebElement> elementos = driver.findElements(By.tagName("a"));	
			for(WebElement elemento : elementos) {
				try {
					String linkProdutos = elemento.getAttribute("href");
					if(linkProdutos.contains("/loja/servico/detalhe")) {
						if(!linksRepetidos.contains(linkProdutos)) {
							file.write(linkProdutos + "\n");	
							linksRepetidos.add(linkProdutos);
						}
					}
				}catch(Exception e) {
					System.out.println("Ignorando valores de NullPointer...");
				}
			}
		}
		file.close();
		linksRepetidos.clear();
		driver.close();
		driver.quit();
		DadosProdutos produtos = new DadosProdutos(Logar.email, Logar.senha);
		produtos.Logar();
		
	}
}
