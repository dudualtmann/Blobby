package control;


import java.util.Scanner;

import org.hibernate.Session;

import model.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		
//		Scanner read = new Scanner(System.in);
//		
//		System.out.println("Digite o Username");
//		String username = read.nextLine();
//		
//		//Friendlist f = new Friendlist();
//		//System.out.println("Digite a Data");
//		//String data = read.nextLine();
//		//System.out.println(f.toNormalDate(data));
//		
//		User u = new User(username,username,username);
//		if(u.cadastrar()) {
//			System.out.println("Cadastrado: "+u.getUsername());
//		}
//		else {
//			System.out.println(u.errorMsg);
//		}
//		
//			read.close();
		
		Session session = HibernateUtil.abrirSession();
		session.close();
		
		

	}

}
