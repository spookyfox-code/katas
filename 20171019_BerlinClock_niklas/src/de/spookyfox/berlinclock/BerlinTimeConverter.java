package de.spookyfox.berlinclock;

import de.spookyfox.berlinclock.dto.BerlinTime;
import de.spookyfox.berlinclock.dto.Time;

public interface BerlinTimeConverter {

	BerlinTime convert(Time time);

}
