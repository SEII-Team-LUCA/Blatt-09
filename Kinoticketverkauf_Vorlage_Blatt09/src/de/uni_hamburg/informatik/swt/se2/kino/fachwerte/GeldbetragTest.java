package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeldbetragTest
{
    Geldbetrag dreiZwanzig = Geldbetrag.valueOf(3, 20);
    Geldbetrag zweiSiebzig = Geldbetrag.valueOf(2, 70);
    Geldbetrag nullNeunzig = Geldbetrag.valueOf(0, 90);

    @Test
    public void testValueOf()
    {
        assertEquals(3, dreiZwanzig.euro());
        assertEquals(20, dreiZwanzig.cent());
    }

    @Test
    public void testToString()
    {
        assertEquals("3,20", dreiZwanzig.toString());
    }

    @Test
    public void testUnvollstaendigerString()
    {
        assertEquals(Geldbetrag.valueOf("3,2").toString(), dreiZwanzig.toString());
    }

    @Test
    public void testMultiplication()
    {
        assertEquals(Geldbetrag.valueOf(12, 80), dreiZwanzig.multipliziere(4));
    }

    @Test
    public void testMultiplicationUebertrag()
    {
        assertEquals(Geldbetrag.valueOf(3, 60), nullNeunzig.multipliziere(4));
    }

    @Test
    public void testAddition()
    {
        assertEquals(Geldbetrag.valueOf(4, 10),
                nullNeunzig.addiere(dreiZwanzig));
    }

    @Test
    public void testSubtraktion()
    {
        assertEquals(Geldbetrag.valueOf(2, 30),
                dreiZwanzig.minus(nullNeunzig));
    }

    @Test
    public void testNegativesErgebnis()
    {
        assertEquals(Geldbetrag.valueOf(-2, 30),
                nullNeunzig.minus(dreiZwanzig));
    }

    @Test
    public void testNotEquals()
    {
        assertNotEquals(Geldbetrag.valueOf(4, 10), Geldbetrag.valueOf(10, 4));
    }

    @Test
    public void testEquals()
    {
        assertTrue(Geldbetrag.valueOf(4, 10).equals(dreiZwanzig.addiere(nullNeunzig)));
    }

    @Test
    public void testNachkommastellen()
    {
        assertNotEquals(Geldbetrag.valueOf(4, 1), Geldbetrag.valueOf(4, 01));
    }

    @Test
    public void testAusStringEinstelligeZahlen()
    {
        assertEquals(Geldbetrag.valueOf(3, 10), Geldbetrag.valueOf("3,10"));
        assertEquals(Geldbetrag.valueOf(3, 10), Geldbetrag.valueOf("3,1"));
        assertNotEquals(Geldbetrag.valueOf(3, 1), Geldbetrag.valueOf("3,01"));
    }

    @Test
    public void testAusStringMehrstelligeZahlen()
    {
        assertEquals(Geldbetrag.valueOf(12, 21), Geldbetrag.valueOf("12,21"));
    }

    @Test(expected = NumberFormatException.class)
    public void testUngueltigerString()
    {
        Geldbetrag.valueOf(",,");
    }

    @Test
    public void testSmaller()
    {
        assertTrue(zweiSiebzig.compareTo(dreiZwanzig) < 0);
    }

    @Test
    public void testEquivalent()
    {
        assertTrue(zweiSiebzig.compareTo(zweiSiebzig) == 0);
    }

    @Test
    public void testGreater()
    {
        assertTrue(dreiZwanzig.compareTo(Geldbetrag.valueOf(3, 19)) > 0);
    }
}
