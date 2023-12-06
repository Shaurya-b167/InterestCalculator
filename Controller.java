package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import java.math.*;

/**
 * Name: Shaurya Beriwala
 * Username: beris01
 */

public class Controller {
	@FXML
    private TextField basePrice;

    @FXML
    private Button calculateBtn;

    @FXML
    private TextField downPayment;

    @FXML
    private Label loanAmt;

    @FXML
    private Label monthlyAmt;

    @FXML
    private RadioButton months24;

    @FXML
    private RadioButton months36;

    @FXML
    private RadioButton months48;

    @FXML
    private RadioButton months60;

    @FXML
    private CheckBox rearCamera;

    @FXML
    private Button resetBtn;

    @FXML
    private TextField salesTax;

    @FXML
    private CheckBox sunRoof;

    @FXML
    private Label totalAmt;

    @FXML
    private CheckBox touchScreen;
    
    @FXML
    public void initialize() {
    	calculateBtn.setOnAction(e -> {
			calculate();
			});
    	resetBtn.setOnAction(e -> {
    		reset();
    	});
    }
    
    @FXML
    public void calculate() {
    	double interestRate= calcInterestRate(), extras= calculateExtras(), 
    			baseAmt= Double.parseDouble(basePrice.getText()), totalLoan, monthlyRate, monthScale,
    			downAmt= Double.parseDouble(downPayment.getText()), monthAmt, totalPayment,
    			taxRate= Double.parseDouble(salesTax.getText()), months;
    	totalLoan= baseAmt + extras + (baseAmt+extras)*taxRate - downAmt;
    	loanAmt.setText(String.format("%.2f", totalLoan));
    	
    	months= interestRate==7? 24 : interestRate==6? 36 : interestRate==5.5? 48 : 60;
    	monthlyRate= interestRate/12;
    	monthScale= Math.pow((1+monthlyRate), months);
    	monthAmt= totalLoan*((monthlyRate*monthScale)/(monthScale-1));
    	monthlyAmt.setText(String.format("%.2f", monthAmt));
    	
    	totalPayment= monthAmt*months;
    	totalAmt.setText(String.format("%.2f", totalPayment));
    }
    
    @FXML
    public void reset() {
    	totalAmt.setText("0.0");
    	monthlyAmt.setText("0.0");
    	loanAmt.setText("0.0");
    	basePrice.setText("");
    	salesTax.setText("");
    	downPayment.setText("");
    	sunRoof.setSelected(false);
    	rearCamera.setSelected(false);
    	touchScreen.setSelected(false);
    	months24.setSelected(false);
    	months36.setSelected(false);
    	months48.setSelected(false);
    	months60.setSelected(false);
    }
    	
    public double calculateExtras() {
    	double extras= 0;
    	if(sunRoof.isSelected()) {
    		extras+= 1510;
    	}
    	if(rearCamera.isSelected()) {
    		extras+= 340;
    	}
    	if(touchScreen.isSelected()) {
    		extras+= 470;
    	}
    	return extras;
    }
    
    public double calcInterestRate() {
    	if(months24.isSelected()) {
    		return 7;
    	}
    	else if(months36.isSelected()) {
    		return 6;
    	}
    	else if(months48.isSelected()) {
    		return 5.5;
    	}
    	else if(months60.isSelected()){
    		return 5;
    	}
    	else {
    		return 0;
    	}
    }
}
