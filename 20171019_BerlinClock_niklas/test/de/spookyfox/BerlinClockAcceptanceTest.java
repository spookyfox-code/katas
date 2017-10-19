package de.spookyfox;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.spookyfox.BerlinClock;
import de.spookyfox.berlinclock.BerlinTimeConverter;
import de.spookyfox.berlinclock.BerlinTimeWriter;
import de.spookyfox.berlinclock.TimeReader;
import de.spookyfox.berlinclock.convert.BerlinTimeConverterImpl;
import de.spookyfox.berlinclock.read.ArgumentTimeReader;
import de.spookyfox.berlinclock.read.Arguments;
import de.spookyfox.berlinclock.write.Console;
import de.spookyfox.berlinclock.write.ConsoleBerlinTimeWriter;

public class BerlinClockAcceptanceTest {

	private String digitalTime;
	private StringBuffer outputBuffer;
	private BerlinClock clock;

	@Before
	public void setUp() {
		outputBuffer = new StringBuffer();

		TimeReader reader = new ArgumentTimeReader(new MockedArguments());
		BerlinTimeWriter writer = new ConsoleBerlinTimeWriter(new MockedConsole());
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
		this.digitalTime = digitalTime;
	}

	private void assertBerlinTime(String expectedBerlinTime) {
		assertEquals(expectedBerlinTime, outputBuffer.toString());
	}

	private class MockedArguments implements Arguments {
		public @Override String[] getArguments() {
			return new String[] { digitalTime };
		}
	}

	private class MockedConsole implements Console {
		public @Override void display(String text) {
			outputBuffer.append(text);
		}
	}

}
