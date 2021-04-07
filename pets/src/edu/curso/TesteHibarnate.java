package edu.curso;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

public class TesteHibarnate {
	
	public static void criarPet(EntityManager em ) {
		Pet b = new Pet();
		b.setEspecie("Cachorro");
		b.setNome("rex");
		b.setRaça("Bull");
		
		Pet b1 = new Pet();
		b1.setEspecie("Passaro");
		b1.setNome("piu piu");
		b1.setRaça("Canario");
		
		em.getTransaction().begin();
		em.persist(b);
		em.persist(b1);
		em.getTransaction().commit();
	}
	
	public static void listarPet(EntityManager em) {
		TypedQuery<Pet> qry =
				em.createQuery("select a from Pet a",Pet.class);
		List<Pet> lista = qry.getResultList();
		for (Pet a : lista) {
			System.out.printf("Nome: %s - Raça: %s - Especie: %s %n", a.getNome(), a.getRaça(), a.getEspecie());;
		}
	}
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PETS");
		EntityManager em = emf.createEntityManager();
		em.clear();
		while(true) {
			int numero = Integer.parseInt(JOptionPane.showInputDialog(null,"Escolha numero"));
			
			if (numero == 1) {
				criarPet(em);
			}
			else if(numero == 2) {
				listarPet(em);
			}
			else if(numero == 3) {
				break;
			}
			
		}
		emf.close();
	}

}
