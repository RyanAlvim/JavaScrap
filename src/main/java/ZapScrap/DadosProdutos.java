package ZapScrap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import Config.Configs;

public class DadosProdutos {
	
	private static WebDriver driver;
	public static String email;
	public static String senha;

	private static ArrayList<String> pegarNome = new ArrayList<String>();
	private static ArrayList<String> UrlGabarito = new ArrayList<String>();
	private static FileWriter file;
	private static ArrayList<String> dados = new ArrayList<String>();
	private static ArrayList<String> precos = new ArrayList<String>();
	private static ArrayList<String> pricefiltred = new ArrayList<String>();
	private static ArrayList<String> nomeUnidade = new ArrayList<String>();
	private static String nome;
	private static String[] referencia;
	private static String[] papel_material;
	private static String[] cores;
	private static String[] gramatura;
	private static String[] tam_arte;
	private static String[] tam_final;
	private static String[] peso;
	private static String[] acabamentos;
	private static String gabarito;

	
	
	public DadosProdutos(String email, String senha) throws IOException {
		DadosProdutos.email = email;
		DadosProdutos.senha = senha;
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--headless");
		driver = new ChromeDriver(option);
		file = new FileWriter(Configs.lista,true);
		file.write("referencia;nome;preco;quantidade;material;cores;gramatura;tamanho_arte;tamanho_final;peso;imagem;gabarito \n");
	}
	
	public void Logar() throws InterruptedException, IOException {
		Scanner scan = new Scanner(new File(Configs.produtos));
		while(scan.hasNextLine()) {
			String linha = scan.nextLine();
			driver.get(linha);
			Thread.sleep(3000);
			break;
		}
		WebElement emailLogar = driver.findElement(By.id("input-login-email"));
		emailLogar.sendKeys(this.email);
		Thread.sleep(300);
		WebElement senhaLogar = driver.findElement(By.id("input-login-senha"));
		senhaLogar.sendKeys(this.senha);
		Thread.sleep(300);
		driver.findElement(By.id("btnEntrarLogin")).click();
		Thread.sleep(5000);
		Informacoes();
	}
	
	
	public void Informacoes() throws IOException, InterruptedException {
		Scanner scan = new Scanner(new File(Configs.produtos));
		while(scan.hasNextLine()) {
			
			
			String linha = scan.nextLine();
			driver.get(linha);
			List<WebElement> nomeProduto = driver.findElements(By.tagName("h1"));
			List<WebElement> btnGabarito = driver.findElements(By.tagName("a"));
			List<WebElement> tagsP = driver.findElements(By.tagName("p"));
			String img = driver.findElement(By.className("jqzoom")).getAttribute("href");
			
			for(WebElement Gabarito : btnGabarito) {
				UrlGabarito.add(Gabarito.getAttribute("href"));
			}
			
			for(WebElement nomeP : nomeProduto) {
				pegarNome.add(nomeP.getText());
			}
			
			precos();
			
			for(WebElement tags : tagsP) {
				if(tags.getText() != "") {
					
					dados.add(tags.getText());
					if(dados.size() == 8) {
						break;
					}
				}
			}
			
			nome = pegarNome.get(2);
			referencia = dados.get(0).split(":");
			papel_material = dados.get(1).split(":");
			cores = dados.get(2).split(":");
			gramatura = dados.get(3).split(":");
			tam_arte = dados.get(4).split(":");
			tam_final = dados.get(5).split(":");
			peso = dados.get(6).split(":");
			acabamentos = dados.get(7).split(":");
			
			for(int i =0; i < UrlGabarito.size(); i++) {
				if(UrlGabarito.get(i) == null) {
					continue;
				}
				if(UrlGabarito.get(i).contains(".zip")){			
					gabarito = UrlGabarito.get(i);
					break;
				}
				
			}
			
			
			for(int j =0; j < pricefiltred.size(); j++) {
			
			System.out.println("Nome: " + nome);
			String referencia_filter = referencia[1].replaceAll("\\r\\n|\\n", "");
			System.out.println("Referência: " + referencia_filter);
			
			String papel_material_filter = papel_material[1].replaceAll("\\r\\n|\\n", "");
			System.out.println("Papel / Material: " + papel_material_filter);
			
			String cores_filter = cores[1].replaceAll("\\r\\n|\\n", "");
			System.out.println("Cores: " + cores_filter);
			
			String gramatura_filter = gramatura[1].replaceAll("\\r\\n|\\n", "");
			System.out.println("Gramatura: " + gramatura_filter);
			
			String tam_arte_filter = tam_arte[1].replaceAll("\\r\\n|\\n", "");
			System.out.println("Tamanho da Arte: " + tam_arte_filter);
			
			String tam_final_filter = tam_final[1].replaceAll("\\r\\n|\\n", "");
			System.out.println("Tamanho Final: " + tam_final_filter);
			
			String peso_filter = peso[1].replaceAll("\\r\\n|\\n", "");
			System.out.println("Peso: " + peso_filter);
			
			String acabamentos_filter = acabamentos[1].replaceAll("\\r\\n|\\n", "");
			System.out.println("Acabamentos: " + acabamentos_filter);
			
			System.out.println("Imagem: " + img);
			
			
			System.out.println("Gabarito: " + gabarito);
			
			String precoFiltrado = pricefiltred.get(j);
			System.out.println("Preço: " + precoFiltrado);
			
			String quantidade = nomeUnidade.get(j);
			System.out.println("Quantidade: " + quantidade);
			
			System.out.println(" ");
			ArquivoCSV(referencia_filter, nome,precoFiltrado, quantidade,papel_material_filter, cores_filter, gramatura_filter, tam_arte_filter, tam_final_filter, peso_filter,img, gabarito);
				}
			
			nomeUnidade.clear();
			dados.clear();
			pegarNome.clear();
			UrlGabarito.clear();
			pricefiltred.clear();
			precos.clear();
			}
		}
	
	
	public void ArquivoCSV(String referencia,String nome,String preco,String quantidade,String material,String cores,
			String gramatura,String tamanho_arte,String tamanho_final,String peso,String imagem,String gabarito) throws IOException {
		
		file.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s", referencia,nome,preco,quantidade,material,cores,gramatura,tamanho_arte,tamanho_final,peso,imagem,gabarito) +"\n");
		file.flush();
	}
	
	
	public void precos() throws InterruptedException, IOException {
		WebElement select = driver.findElement(By.id("slcQuantidade"));
		Select selectObject = new Select(select);
		List <WebElement> elementCount = selectObject.getOptions();	
		List<WebElement> preco = driver.findElements(By.tagName("span"));
			
		for(int i =0; i <= elementCount.size()-1; i++) {
			selectObject.selectByIndex(i);
			for(WebElement Preco : preco) {
				precos.add(Preco.getText());
			}
			
			for(String precop : precos) {
				if(!(precop == "")) {
					if(precop.contains("R$")) {
						FileWriter ArquivoPreco = new FileWriter(Configs.precos);
						ArquivoPreco.write(precop);
						ArquivoPreco.flush();
					}if(precop.contains("unid.")) {
						System.out.println(precop);
					}
				}
			}
			FileWriter ArquivoUnidade = new FileWriter(Configs.unidades);
			ArquivoUnidade.write(select.getText());
			ArquivoUnidade.flush();
			Scanner scan = new Scanner(new File(Configs.unidades));
			while(scan.hasNextLine()) {
				String linha = scan.nextLine();
				nomeUnidade.add(linha);
			}
			List<String> linhas = Files.readAllLines(new File(Configs.precos).toPath());
	        pricefiltred.add(linhas.get(linhas.size() - 1));
		}
	}
}
