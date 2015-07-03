package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.fachwerte;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class GeldbetragTest
{
    Geldbetrag dreiZwanzig = Geldbetrag.valueOf(3, 20);
    Geldbetrag zweiSiebzig = Geldbetrag.valueOf(2, 70);
    Geldbetrag nullNeunzig = Geldbetrag.valueOf(0, 90);
    
    @Test
    public void testValueOf()
    {
        assertEquals(3, threeQuarters.euro());
        assertEquals(20, threeQuarters.cent());
    }
    
    @Test
    public void testToString()
    {
        assertEquals("3,20", threeQuarters.toString());
        //  assertEquals("5 / 8", Rational.valueOf(15, 24).toString());
    }
    
    @Test
    public void testMultiplication()
    {
        assertEquals(Rational.valueOf(15, 24), threeQuarters.times(fiveSixths));
    }
    
    @Test
    public void testDivision()
    {
        assertEquals(Rational.valueOf(10, 9), fiveSixths.over(threeQuarters));
    }
    
    @Test
    public void testAddition()
    {
        assertEquals(Rational.valueOf(19, 12), fiveSixths.plus(threeQuarters));
    }
    
    @Test
    public void testCollection()
    {
        Set<Rational> set = new HashSet<Rational>();
        set.add(Rational.valueOf(22, 7));
        assertTrue(set.contains(Rational.valueOf(22, 7)));
    }
    
    @Test
    public void testNotEquals()
    {
        assertNotEquals(Rational.valueOf(5, 7), Rational.valueOf(7, 5));
    }
    
    @Test
    public void testAusStringEinstelligeZahlen()
    {
        assertEquals(Rational.valueOf(1, 3), Rational.valueOf("1 / 3"));
    }
    
    @Test
    public void testAusStringMehrstelligeZahlen()
    {
        assertEquals(Rational.valueOf(12, 34), Rational.valueOf("12 / 34"));
    }
    
    // TADA, unser erster Negativ-Test im SE-Zyklus!!!
    @Test(expected = IllegalArgumentException.class)
    public void testUngueltigerString()
    {
        Rational.valueOf("1 / 0");
    }
    
    @Test
    public void testSmaller()
    {
        assertTrue(oneHalf.compareTo(threeQuarters) < 0);
    }
    
    @Test
    public void testEquivalent()
    {
        assertTrue(oneHalf.compareTo(oneHalf) == 0);
    }
    
    @Test
    public void testGreater()
    {
        assertTrue(threeQuarters.compareTo(oneHalf) > 0);
    }
}
