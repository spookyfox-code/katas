package de.spookyfox;

import de.spookyfox.berlinclock.BerlinTimeConverter;
import de.spookyfox.berlinclock.BerlinTimeWriter;
import de.spookyfox.berlinclock.TimeReader;
import de.spookyfox.berlinclock.dto.BerlinTime;
import de.spookyfox.berlinclock.dto.Time;

public class BerlinClock {

	private final TimeReader reader;
	private final BerlinTimeConverter converter;
	private final BerlinTimeWriter writer;

	public BerlinClock(TimeReader reader, BerlinTimeConverter converter, BerlinTimeWriter writer) {
		this.reader = reader;
		this.converter = converter;
		this.writer = writer;
	}

	public void tick() {
		Time time = reader.read();
		BerlinTime berlinTime = converter.convert(time);
		writer.write(berlinTime);
	}

}
