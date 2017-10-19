package de.spookyfox;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.spookyfox.berlinclock.BerlinTimeConverter;
import de.spookyfox.berlinclock.BerlinTimeWriter;
import de.spookyfox.berlinclock.TimeReader;
import de.spookyfox.berlinclock.convert.BerlinTimeConverterImpl;
import de.spookyfox.berlinclock.read.ArgumentTimeReader;
import de.spookyfox.berlinclock.read.Arguments;
import de.spookyfox.berlinclock.write.Console;
import de.spookyfox.berlinclock.write.ConsoleBerlinTimeWriter;

public class BerlinClockAcceptanceTest {

	private BerlinClock clock;

	private MockedArguments arguments;
	private MockedConsole console;

	@Before
	public void setUp() {
		arguments = new MockedArguments();
		console = new MockedConsole();

		TimeReader reader = new ArgumentTimeReader(arguments);
		BerlinTimeWriter writer = new ConsoleBerlinTimeWriter(console);
		BerlinTimeConverter converter = new BerlinTimeConverterImpl();

		clock = new BerlinClock(reader, converter, writer);
	}

	@Test
	public void berlinTimeConvertion() {
		arrangeDigitalTime("13:21:25");
		clock.tick();
		assertBerlinTime("Y\nRROO\nRRRO\nYYRYOOOOOOO\nYOOO");
	}

	private void arrangeDigitalTime(String digitalTime) {
		arguments.digitalTime = digitalTime;
	}

	private void assertBerlinTime(String expectedBerlinTime) {
		assertEquals(expectedBerlinTime, console.text);
	}

	private class MockedArguments implements Arguments {
		public String digitalTime;

		public @Override String[] getArguments() {
			return new String[] { digitalTime };
		}
	}

	private class MockedConsole implements Console {
		public String text;

		public @Override void display(String text) {
			this.text = text;
		}
	}

}
