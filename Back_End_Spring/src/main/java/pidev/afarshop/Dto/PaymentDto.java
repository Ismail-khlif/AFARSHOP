package pidev.afarshop.Dto;

import lombok.Builder;
import lombok.Data;
import pidev.afarshop.Entity.PaymentMethod;
import java.util.Date;

@Data
@Builder
public class PaymentDto {

    private Long paymentId;

    private boolean paid;

    private Date paymentDate;

    private double installmentAmount;

    private PaymentMethod paymentMethod;

    private Date dueDate;

    private BillDto billPayment;

}
