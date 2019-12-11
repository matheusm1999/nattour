package BO;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;  

public class Validar {

	public String Whatsapp(int ddi, int ddd, int Numero) {

		return "http://api.whatsapp.com/send?1=pt_BR&phone=" + ddi + ddd + Numero;
	}
	
	public boolean ValidaCPF(String CPF) {

		String cpf = "\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d";
		boolean validacpf = CPF.matches(cpf);
		System.out.println("Retorno CPF = " + validacpf);

		return validacpf;
	}
	
	public boolean ValidaCEP(String CEP) {

		String cep = "\\d\\d\\d\\d\\d-\\d\\d\\d";
		boolean valida = CEP.matches(cep);
		System.out.println(valida);

		return valida;

	}

	@SuppressWarnings("deprecation")
	public boolean ValidaData(String datainicio, String datafim) {
		long millis=System.currentTimeMillis();  
		java.sql.Date dataAtual = new java.sql.Date(millis);  
		String dtAtual = dataAtual.toString(); 
		
		System.out.println("DATA ATUAL: " + dtAtual);
		//System.out.println("DATA INICIO: " + date1);
		//System.out.println("DATA FIM: " + date2);
		/*
		if(dataAtuaal.after(date1))
			return false;
			
		if(dataAtuaal.after(date2))
			return false;
		*/
        
		
		int anoi = Integer.parseInt(datainicio.substring(0, 4));
		System.out.println("ANOOOOIIIII " + datainicio.substring(0, 4));
		int anof = Integer.parseInt(datafim.substring(0, 4));
		int anoa = Integer.parseInt((dtAtual.substring(0, 4)));
		if (anoi != anof || anoa > anof || anoa > anoi){
			System.out.println("Ano atual maior");
			return false;
		}	
			
		int mesi = Integer.parseInt(datainicio.substring(5, 7));
		System.out.println("MEEEEESSSIIIII" + datainicio.substring(5, 7));
		int mesf = Integer.parseInt(datafim.substring(5, 7));
		int mesa = Integer.parseInt((dtAtual.substring(5, 7)));
		if (mesi != mesf || mesa > mesf || mesa > mesi){
			System.out.println("Mes atual maior");
			return false;
		}	
			
		int diai = Integer.parseInt(datainicio.substring(8, 10));
		System.out.println("MEEEEESSSIIIII: " + datainicio.substring(8, 10));
		System.out.println("MEEEEESSSIIIII: " + diai);
		int diaf = Integer.parseInt(datafim.substring(8, 10));
		int diaa = Integer.parseInt((dtAtual.substring(8, 10)));
		if (diai != diaf || diaa > diaf || diaa > diai){ 
			System.out.println("Dia atual maior");
			return false;
		}
	
		return true;
	}

	public boolean ValidaHora(String horainicio, String horafim) {
		int horas = LocalDateTime.now().getHour();
		horas = horas - 1;
		int minutos = LocalDateTime.now().getMinute();
		
		System.out.println("Horas:" + horas);       // 7
	    System.out.println("Minutos: " + minutos);     // 45
	    //System.out.println(LocalDateTime.now().getSecond());     // 32
		
		int horai = Integer.parseInt(horainicio.substring(0, 2));
		int horaf = Integer.parseInt(horafim.substring(0, 2));
		int mini = Integer.parseInt(horainicio.substring(3, 5));
		int minf =  Integer.parseInt(horainicio.substring(3, 5));
		
		System.out.println("HORAI: " + horainicio.substring(0, 2));
		System.out.println("HORAI: " + horai);
		System.out.println("HORAF: " + horainicio.substring(0, 2));
		System.out.println("HORAI: " + horaf);
		
		if (horaf - horai > 4 || horas > horaf || horas > horai){
			System.out.println("Hora atual maior que a inicial ou final");
			System.out.println("Comparando: horas" + horas + " horaf: " + horaf + " horai: " + horai);
			return false;
		}
		if(horai == horas && minutos > mini || horaf == horas && minutos > minf){
			System.out.println("Comparando: minutos:" + minutos + " mini: " + mini + " minf: " + minf);
			System.out.println("Minuto atual maior que o inicial ou final");
			return false;
		}
		if(horai == horaf && mini == minf){
			System.out.println("Hora inicial e final iguais");
			System.out.println("Minuto inicial e final iguais");
			return false;
		}
		
		return true;
	}

	public double ConverteValor(String valor) {

		return Double.parseDouble(valor);
	}

	public boolean ValidaObservacao(String Obs) {

		return Obs.matches("[A-Z]{1}[a-z|0-9| |.|,|!|?|áàâãéèêíïóôõöúçñ]+");
	}

	public boolean ValidaTitulo(String titulo) {

		return titulo.matches("[A-Z]{1}[a-z| |áàâãéèêíïóôõöúçñ]+");
	}

	public static void main(String[] args) {
		boolean nome = "Maria".matches("Maria");
		System.out.println("Retorno = " + nome);

		/*
		 * String cep = "\\d\\d\\d\\d\\d-\\d\\d\\d"; boolean valida =
		 * "99855-000".matches(cep); System.out.println(valida);
		 * 
		 * String cpf = "\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d"; boolean validacpf =
		 * "113.983.069-40".matches(cpf);
		 * System.out.println("Retorno CPF = "+validacpf);
		 */

		String datainicio = "1998-10-28";

		String datafim = "1998-10-27";
		/*
		 * System.out.println(datainicio.substring(0, 4));
		 * System.out.println(datafim.substring(0, 4));
		 * System.out.println(datainicio.substring(5, 7));
		 * System.out.println(datafim.substring(5, 7));
		 * System.out.println(datainicio.substring(8, 10));
		 * System.out.println(datafim.substring(8, 10));
		 */

		String horainicio = "18:40";
		String horafim = "22:00";
		System.out.println(horainicio.substring(0, 2));
		System.out.println(horafim.substring(0, 2));

		Validar v = new Validar();
		//System.out.println(v.Whatsapp("55", "45", "998224972"));
		boolean i = v.ValidaCPF("113.983.069-40");
		System.out.println(i);
		i = v.ValidaCEP("99855-000");
		System.out.println(i);

		System.out.println(v.ValidaData("1998-10-28", "1998-10-10"));
		System.out.println(v.ValidaHora("15:30", "16:30"));
		System.out.println("OBS: " + v.ValidaObservacao("Eusou 123?!"));
		System.out.println("Titulo:"+ v.ValidaTitulo("Quem faz em bbb é aaa"));
	}

}
