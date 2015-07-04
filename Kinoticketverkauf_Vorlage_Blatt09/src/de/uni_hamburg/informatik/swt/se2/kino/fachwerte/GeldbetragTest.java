package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Eine Testklasse für Geldbeträge.
 * 
 * @author Utz
 *
 */
public class GeldbetragTest
{
    Geldbetrag dreiZwanzig = Geldbetrag.valueOf(3, 20);
    Geldbetrag zweiSiebzig = Geldbetrag.valueOf(2, 70);
    Geldbetrag nullNeunzig = Geldbetrag.valueOf(0, 90);
    Geldbetrag einsVierzig = Geldbetrag.valueOf(1, 40);
    Geldbetrag minusDreiZwanzig = Geldbetrag.valueOf(-3, 20);
    Geldbetrag minusZweiSiebzig = Geldbetrag.valueOf(-2, 70);

    /**
     * Testet, ob die Methode zum erstellen der Geldbeträge funktioniert.
     */
    @Test
    public void testValueOf()
    {
        assertEquals(Geldbetrag.valueOf(320), Geldbetrag.valueOf(3, 20));
        assertEquals(Geldbetrag.valueOf(3, 20), Geldbetrag.valueOf("3,20"));
        assertEquals(Geldbetrag.valueOf("3,20"), Geldbetrag.valueOf(320));
    }

    /**
     * Testet, ob die Werte in einem Geldbetrag stimmen.
     */
    @Test
    public void testWerte()
    {
        assertEquals(3, dreiZwanzig.euro());
        assertEquals(20, dreiZwanzig.cent());
        assertEquals(320, dreiZwanzig.eurocent());
    }

    /**
     * Testet, ob die Werte in einem Geldbetrag stimmen.
     */
    @Test
    public void testNegativeWerte()
    {
        assertEquals(-3, minusDreiZwanzig.euro());
        assertEquals(-20, minusDreiZwanzig.cent());
        assertEquals(-320, minusDreiZwanzig.eurocent());
    }

    /**
     * Testet, ob Strings richtig eingelesen werden.
     */
    @Test
    public void testToString()
    {
        assertEquals("3,20", dreiZwanzig.toString());
    }

    /**
     * Testet, ob unvollständige Strings richtig eingelesen werden.
     */
    @Test
    public void testUnvollstaendigerString()
    {
        assertEquals(Geldbetrag.valueOf("3,2")
            .toString(), dreiZwanzig.toString());
    }

    /**
     * Testet, ob die Multiplikation funktioniert.
     */
    @Test
    public void testMultiplication()
    {
        assertEquals(Geldbetrag.valueOf(12, 80), dreiZwanzig.multipliziere(4));
    }

    /**
     * Testet, ob der Übertrag bei der Multiplikation bedacht wird.
     */
    @Test
    public void testMultiplicationUebertrag()
    {
        assertEquals(Geldbetrag.valueOf(3, 60), nullNeunzig.multipliziere(4));
    }

    /**
     * Testet, ob die Addition funktioniert.
     */
    @Test
    public void testAddition()
    {
        assertEquals(Geldbetrag.valueOf(6, 40),
                dreiZwanzig.addiere(dreiZwanzig));
    }

    /**
     * Testet, ob der Übertrag bei der Addition bedacht wird.
     */
    @Test
    public void testAdditionUebertrag()
    {
        assertEquals(Geldbetrag.valueOf(4, 10),
                nullNeunzig.addiere(dreiZwanzig));
    }

    /**
     * Testet, ob die Addition bei negativen Geldwerten funktioniert.
     */
    @Test
    public void testAdditionNegativ()
    {
        assertEquals(Geldbetrag.valueOf(-5, 90),
                minusDreiZwanzig.addiere(minusZweiSiebzig));
    }

    /**
     * Testet, ob der Übertrag bei der Addition von negativen Geldwerten funktioniert.
     */
    @Test
    public void testAdditionNegativUebertrag()
    {
        assertEquals(Geldbetrag.valueOf(-5, 40),
                minusZweiSiebzig.addiere(minusZweiSiebzig));
    }

    /**
     * Testet, ob die Subtraktion funktioniert.
     */
    @Test
    public void testSubtraktion()
    {
        assertEquals(Geldbetrag.valueOf(1, 30),
                zweiSiebzig.subtrahiere(einsVierzig));
    }

    /**
     * Testet, ob der Übertrag bei der Subtraktion bedacht wird.
     */
    @Test
    public void testSubtraktionUebertrag()
    {
        assertEquals(Geldbetrag.valueOf(2, 30),
                dreiZwanzig.subtrahiere(nullNeunzig));
    }

    /**
     * Testet, ob die Subtraktion bei negativen Werten funktioniert.
     */
    @Test
    public void testSubtraktionNegativ()
    {
        assertEquals(Geldbetrag.valueOf(0, 50),
                minusZweiSiebzig.subtrahiere(minusDreiZwanzig));
    }

    /**
     * Testet, ob der Übertrag bei der Subtraktion bei negativen Werten bedacht wird.
     */
    @Test
    public void testSubtraktionNegativUebertrag()
    {
        assertEquals(Geldbetrag.valueOf(0, 0),
                minusZweiSiebzig.subtrahiere(minusZweiSiebzig));
    }

    /**
     * Testet, ob auch Negative Ergebnisse gültig sind.
     */
    @Test
    public void testNegativesErgebnis()
    {
        assertEquals(Geldbetrag.valueOf(-2, 30),
                nullNeunzig.subtrahiere(dreiZwanzig));
    }

    /**
     * Testet, ob zwei unterschiedliche Geldwerte auch unterschiedlich sind.
     */
    @Test
    public void testNotEquals()
    {
        assertNotEquals(Geldbetrag.valueOf(4, 10), Geldbetrag.valueOf(10, 4));
    }

    /**
     * Testet, ob zwei gleiche Geldbeträge auch gleich sind.
     */
    @Test
    public void testEquals()
    {
        assertTrue(Geldbetrag.valueOf(4, 10)
            .equals(dreiZwanzig.addiere(nullNeunzig)));
    }

    /**
     * Testet, ob in einem Eingabestring die erste Nachkommastelle die Zehnerstelle des Centwertes ist.
     */
    @Test
    public void testNachkommastellen()
    {
        assertNotEquals(Geldbetrag.valueOf("4,1"), Geldbetrag.valueOf("4,01"));
    }

    /**
     * Testet, ob Eingabestrings mit maximal einer Nachkommastelle =|= 0 richtig aufgenommen werden.
     */
    @Test
    public void testAusStringEinstelligeZahlen()
    {
        assertEquals(Geldbetrag.valueOf(3, 10), Geldbetrag.valueOf("3,10"));
        assertEquals(Geldbetrag.valueOf(3, 10), Geldbetrag.valueOf("3,1"));
        assertEquals(Geldbetrag.valueOf(3, 1), Geldbetrag.valueOf("3,01"));
    }

    /**
     * Testet, ob Eingabestrings mit beliebigen Nachkommastellen richtig aufgenommen werden.
     */
    @Test
    public void testAusStringMehrstelligeZahlen()
    {
        assertEquals(Geldbetrag.valueOf(12, 21), Geldbetrag.valueOf("12,21"));
    }

    /**
     * Stellt sicher, dass verschiedene ungültige Eingabestrings nicht aufgenommen werden.
     */
    @Test(expected = NumberFormatException.class)
    public void testUngueltigerString1()
    {
        Geldbetrag.valueOf("9,888");
    }

    /**
     * Stellt sicher, dass verschiedene ungültige Eingabestrings nicht aufgenommen werden.
     */
    @Test(expected = NumberFormatException.class)
    public void testUngueltigerString2()
    {
        Geldbetrag.valueOf("asdpi");
    }

    /**
     * Stellt sicher, dass verschiedene ungültige Eingabestrings nicht aufgenommen werden.
     */
    @Test(expected = NumberFormatException.class)
    public void testUngueltigerString3()
    {
        Geldbetrag.valueOf("9,,");
    }

    /**
     * Testet, ob ein Geldbetrag kleiner ist als ein anderer.
     */
    @Test
    public void testSmaller()
    {
        assertTrue(zweiSiebzig.compareTo(dreiZwanzig) < 0);
    }

    /**
     * Testet, ob ein negativer Geldbetrag kleiner ist als ein anderer.
     */
    @Test
    public void testSmallerNegativ()
    {
        assertTrue(minusDreiZwanzig.compareTo(minusZweiSiebzig) < 0);
    }

    /**
     * Testet, ob ein Geldbetrag gleich einem andereren ist.
     */
    @Test
    public void testEquivalent()
    {
        assertTrue(minusDreiZwanzig.compareTo(minusDreiZwanzig) == 0);
    }

    /**
     * Testet, ob ein Geldbetrag größer ist als ein anderer.
     */
    @Test
    public void testGreater()
    {
        assertTrue(minusZweiSiebzig.compareTo(minusDreiZwanzig) > 0);
    }
}
