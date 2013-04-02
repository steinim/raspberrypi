package no.bekk;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class RaspberryMain {

	public static void main(String[] args) throws InterruptedException {

		GpioController gpioController = GpioFactory.getInstance();
		GpioPinDigitalOutput gpio0 = gpioController.provisionDigitalOutputPin(
				RaspiPin.GPIO_00, "gpio0", PinState.LOW);
		while (true) {
			gpio0.setState(PinState.LOW);
			Thread.sleep(1000);
			gpio0.setState(PinState.HIGH);
			Thread.sleep(1000);
		}

	}

}