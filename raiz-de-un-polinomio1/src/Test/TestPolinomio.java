package Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Polinomio;

class TestPolinomio {
//

	private Polinomio P;



	public void escenario1() {
		double[] c=new double[3];
		c[0]=-4;
		c[1]=3;
		c[2]=1;
		
		P=new Polinomio(c);
		
	}


	public void escenario2() {
		double[] c=new double[7];
		c[0]=4;
		c[1]=3;
		c[2]=1;
		c[3]=1;
		c[4]=1;
		
		P=new Polinomio(c);
		
	}
	
	
	

	@Test
	public void TestGetRoots1() {
		escenario1();
		P.MetodoCuadratica();
		assertEquals(P.getRootsP()[1]+"","1.0"); 
			
	}
	
	@Test
	public void TestGetRoots2() {
		escenario2();
		P.Bairstow();
        int raiz=(int) Math.round(P.getRootsP()[3]);
		assertEquals(raiz+"","1");
	}


	@Test
	public void TestComplexRoots1() {
		escenario1();
		P.MetodoCuadratica();
		assertFalse(P.getComplexRoots());
	}
	
	@Test
	public void TestComplexRoots2() {
		escenario2();
		P.Bairstow();
      assertTrue(P.getComplexRoots());
	}

}
