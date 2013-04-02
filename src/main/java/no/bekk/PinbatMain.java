package no.bekk;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class PinbatMain {

	public static void main(String[] args) throws InterruptedException {

		GpioController gpioController = GpioFactory.getInstance();

		List<GpioPinDigitalOutput> gpios = new ArrayList<GpioPinDigitalOutput>(
				5);
		gpios.add(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_00,
				"gpio0", PinState.LOW));
		gpios.add(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02,
				"gpio2", PinState.LOW));
		gpios.add(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03,
				"gpio3", PinState.LOW));
		gpios.add(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01,
				"gpio1", PinState.LOW));
		gpios.add(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04,
				"gpio4", PinState.LOW));

		int tries = 1;
		while (true) {
			Set<String> results = shineTheLights(gpios, tries);

			Console console = System.console();
			String answer = console.readLine("Which light(s) shone?:");

			Set<String> answers = new HashSet<String>(Arrays.asList(answer.split(",")));
			
			if (answers.equals(results)) {
				System.out.println("Correct!");
				tries++;
			} else {
				System.out.println("Wrong! Correct answer was: " + results);
				tries = 1;
			}
			console.readLine("Press enter to continue:");
		}

	}

	private static Set<String> shineTheLights(List<GpioPinDigitalOutput> lights, int tries)
			throws InterruptedException {
		if (tries == 5) {
			System.out.println("You're the champion :)");
			System.exit(0);
		}
		List<String> results = new ArrayList<String>(tries);
		for (int i = 0; i < tries; i++) {
			int randomNum = 0 + (int) (Math.random() * 4);
			lights.get(randomNum).setState(PinState.HIGH);
			Thread.sleep(500);
			lights.get(randomNum).setState(PinState.LOW);
			results.add(randomNum+"");
		}
		return new HashSet<String>(results);
	}

}
