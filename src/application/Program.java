package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		//Criada lista de produtos
		List<Product> list = new ArrayList<>();
		
		//Achar caminho do arquivo
		System.out.println("Enter file path: ");
		String sourceFileStr = sc.nextLine();
		//     sourceFileStr tem o caminho do arquivo
		
		//Instanciar caminho do arquivo numa variavel do tipo File
		File sourceFile = new File(sourceFileStr);
		
		String sourceFolderStr = sourceFile.getParent();
		//     sourceFolderStr tem o caminho antes da pasta
		
		boolean success = new File(sourceFolderStr + "\\out").mkdir();
		//                criando nova pasta chamada out     
		
		String targetFileStr = sourceFolderStr + "\\out\\summary.csv";
		//cria um arquivo .csv
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))) {

			String itemCsv = br.readLine();//lê a linha 1
			while (itemCsv != null) {

				String[] fields = itemCsv.split(",");
				String name = fields[0];//primeira escrita
				double price = Double.parseDouble(fields[1]);//segunda escrita
				int quantity = Integer.parseInt(fields[2]);//terceira escrita

				list.add(new Product(name, quantity, price));//listando produto

				itemCsv = br.readLine();//lê a proxima linha
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {

				for (Product item : list) {//para cada item da lista
					bw.write(item.getName() 
							+ "," 
							+ String.format("%.2f", item.total()));
							//escreve o nome e o total do item
					bw.newLine();//pular linha
				}

				System.out.println(targetFileStr + " CREATED!");
				//                 caminho do arquivo criado
				
			} catch (IOException e) {
				System.out.println("Error writing file: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		
		
		
		
		sc.close();
	}

}
