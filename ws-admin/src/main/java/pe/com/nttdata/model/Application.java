package pe.com.nttdata.model;

import lombok.Data;

@Data
public class Solicitud {
    private String id;
    private String amount;
    private String payMode;
    private String status; // cuando el cliente acepte se establece en true para la transaccion de compra
    private String transactionNumber;
}
