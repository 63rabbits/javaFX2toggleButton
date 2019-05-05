package javaFX2toggleButton;

import javafx.beans.binding.ObjectBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ToggleButtonController {

	@FXML
	private ToggleButton tgb;
	@FXML
	private TextField txf;

	@FXML
	private ToggleButton tgbHBind;
	@FXML
	private TextField txfHBind;

	@FXML
	private ToggleButton tgbLBind;
	@FXML
	private TextField txfLBind;

	private Image imgPowerOff;
	private Image imgPowerOn;

	private String on = "ON";
	private String off = "OFF";

	@FXML
	void initialize() {
		imgPowerOff = new Image(this.getClass().getResourceAsStream("res/powerOff.png"));
		imgPowerOn = new Image(this.getClass().getResourceAsStream("res/powerOn.png"));

		// Using Event Handler
		assert tgb != null : "fx:id=\"tgb\" was not injected: check your FXML file 'ToggleButton.fxml'.";
		assert txf != null : "fx:id=\"txf\" was not injected: check your FXML file 'ToggleButton.fxml'.";
		this.tgb.setGraphic(new ImageView(imgPowerOff));
		this.tgb.setSelected(false);
		this.txf.setText(off);

		// Using Bind (High-level API)
		assert tgbHBind != null : "fx:id=\"tgbHBind\" was not injected: check your FXML file 'ToggleButton.fxml'.";
		assert txfHBind != null : "fx:id=\"txfHBind\" was not injected: check your FXML file 'ToggleButton.fxml'.";
		this.tgbHBind.setGraphic(new ImageView(imgPowerOff));
		this.tgbHBind.setSelected(false);
		this.txfHBind.textProperty().bind(this.tgbHBind.selectedProperty().asString());

		// Using Bind (Low-level API)
		assert tgbLBind != null : "fx:id=\"tgbLBind\" was not injected: check your FXML file 'ToggleButton.fxml'.";
		assert txfLBind != null : "fx:id=\"txfLBind\" was not injected: check your FXML file 'ToggleButton.fxml'.";
		this.tgbLBind.setGraphic(new ImageView(imgPowerOff));
		this.tgbLBind.setSelected(false);
		this.txfLBind.textProperty().bind(this.observer(tgbLBind));
	}

	// Using Event Handler
	@FXML
	void tgbOnAction(ActionEvent event) {
		if (this.tgb.isSelected()) {
			this.txf.setText(on);
			this.tgb.setGraphic(new ImageView(imgPowerOn));
		}
		else {
			this.txf.setText(off);
			this.tgb.setGraphic(new ImageView(imgPowerOff));
		}
	}

	// Using Bind (High-level API)

	// Using Bind (Low-level API)
	private ObjectBinding<String> observer(ToggleButton p) {
		final ToggleButton tgb = p;
		ObjectBinding<String> sBinding = new ObjectBinding<String>() {
			{
				super.bind(tgb.selectedProperty());
			}

			@Override
			protected String computeValue() {
				String s;
				if (tgb.isSelected()) {
					s = on;
					tgbLBind.setGraphic(new ImageView(imgPowerOn));

				}
				else {
					s = off;
					tgbLBind.setGraphic(new ImageView(imgPowerOff));
				}
				return s;
			}
		};
		return sBinding;
	}
}
