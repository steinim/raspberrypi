package no.bekk;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class ButtonMain {

	static GpioController gpioController = GpioFactory.getInstance();
	
	public static void main(String[] args) throws InterruptedException {

		

		final GpioPinDigitalOutput gpio = gpioController.provisionDigitalOutputPin(
				RaspiPin.GPIO_01, "gpio1", PinState.LOW);

		final GpioPinDigitalInput myButton = gpioController
				.provisionDigitalInputPin(RaspiPin.GPIO_00,
						PinPullResistance.PULL_DOWN);

		// create and register gpio pin listener
		myButton.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(
					GpioPinDigitalStateChangeEvent event) {
				if (myButton.isHigh()) {
					gpio.toggle();
				}
			}

		});

		while (true) {
			Thread.sleep(50);
		}

	}
	
}