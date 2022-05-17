package pe.com.nttdata.response;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ConsultaSaldo {
	
	private Double saldoTotal;
	private String nombreUsuario;
	private String type;
	private String account ;
	private LocalDateTime fechaConsulta;

}
