package pphvaz.lojaspring;

import pphvaz.lojaspring.util.ValidaCPF;
import pphvaz.lojaspring.util.ValidaCNPJ;

public class TesteCpfCnpj {
	public static void main(String[] args) {
		boolean isCnpj = ValidaCNPJ.isCNPJ("71.276.561/0001-66");
		
		System.out.println("É válido: " + isCnpj);
	
		boolean isCpf = ValidaCPF.isCPF("474.504.568-93");
		
		System.out.println("É válido: " + isCpf);
	}
}