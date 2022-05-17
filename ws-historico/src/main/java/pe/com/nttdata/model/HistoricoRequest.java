package pe.com.nttdata.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class HistoricoRequest {
	private double montoActual;
	private String idOpreracion;
	private LocalDateTime fechaOperacion;
	private String idCliente;
	private String lugarOperacion;
}
