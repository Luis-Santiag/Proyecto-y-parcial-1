
public class main {
	

	    public static int[] encontrar(int[] nums) {

	        int menor = Integer.MAX_VALUE;
	        int segundoMenor = Integer.MAX_VALUE;

	        int mayor = Integer.MIN_VALUE;
	        int segundoMayor = Integer.MIN_VALUE;

	        for (int n : nums) {

	           
	            if (n < menor) {
	                segundoMenor = menor;
	                menor = n;
	            } else if (n > menor && n < segundoMenor) {
	                segundoMenor = n;
	            }

	
	            if (n > mayor) {
	                segundoMayor = mayor;
	                mayor = n;
	            } else if (n < mayor && n > segundoMayor) {
	                segundoMayor = n;
	            }
	        }

	        return new int[]{segundoMenor, segundoMayor};
	    }

	    public static void main(String[] args) {

	        int[] nums = {7, 2, 9, 4, 1, 8};

	        int[] resultado = encontrar(nums);

	        System.out.println("[" + resultado[0] + ", " + resultado[1] + "]");
	    }
	}

