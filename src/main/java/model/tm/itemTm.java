package model.tm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class itemTm extends RecursiveTreeObject<itemTm> {
    private String code;
    private String description;
    private double unitPrice;
    private int qty;
    private JFXButton btn;

    public itemTm(String code, String description, double unitPrice, int qty) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }
}
