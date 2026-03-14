
public class main {
	
		    public static int calcularPuntaje(int[] numeros) {

		        int puntaje = 0;

		        for (int n : numeros) {

		            if (n == 5) {
		                puntaje += 5;
		            } 
		            else if (n % 2 == 0) {
		                puntaje += 1;   
		            } 
		            else {
		                puntaje += 3;
		            }

		        }

		        return puntaje;
		    }

		    public static void main(String[] args) {

		        int[] numeros = {5,5,5};

		        int resultado = calcularPuntaje(numeros);

		        System.out.println("Puntaje total: " + resultado);
		    }
		    
		    //Complejidad temporal
		    //La complejidad es de "complejidad temporal 0(n)" indica que el tiempo de ejecucion depende del tamñano de del arreglo 
		    
		    
		    //complejidad espacial
		    //la complejidad es de "complejidad espacial 0(1)" porque uso es constante 
		    
		    
		    
		    

}
