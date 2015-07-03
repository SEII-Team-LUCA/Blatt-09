package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Geldbetrag implements Comparable<Geldbetrag>
{
    private final int _euro;
    private final int _cent;
    private final int _eurocent;

    private Geldbetrag(int euro, int cent)
    {
        _euro = euro;
        _cent = cent;
        _eurocent = (euro * 100) + cent;
    }
    
    private Geldbetrag(int eurocent)
    {
        _euro = (int)(eurocent / 100);
        _cent = eurocent % 100;
        _eurocent = eurocent;
    }

    public static Geldbetrag valueOf(int euro, int cent)
    {
        return new Geldbetrag(euro, cent);
    }

    public int euro()
    {
        return _euro;
    }

    public int cent()
    {
        return _cent;
    }
    
    public int eurocent()
    {
        return _eurocent;
    }

    @Override
    public String toString()
    {
        return _euro + "," + _cent;
    }

    public Geldbetrag multipliziere(int faktor)
    {
        int cent = this._cent * faktor;
        int euro;
        if (cent > 99)
        {
            euro = _euro * faktor + (int) (cent / 100);
        }
        else
        {
            euro = _euro * faktor;
        }
        return new Geldbetrag(euro, cent);
    }

    /**
     * Erstellt einen neuen Geldbetrag aus einem gegebenen Euro- und Cent-Wert
     *
     * @param int euro: Euro-Stellen des neuen Geldbetrages
     * @param int cent: Cent-Stellen des neuen Geldbetrages
     *
     * @return Einen neuen Geldbetrag aus den gegebenen Werten
     */
    public Geldbetrag einlesen(int euro, int cent)
    {
        return new Geldbetrag(euro, cent);
    }

    public Geldbetrag einlesen(String str)
    {
        // TODO insert readIn
        return null;
    }

    @Override
    public boolean equals(Object obj)
    {
        return (obj instanceof Geldbetrag) && equals((Geldbetrag) obj);
    }

    public boolean equals(Geldbetrag that)
    {
        return this._eurocent == that._eurocent;
    }

    // Wenn a.equals(b) gilt, dann muss auch a.hashCode() == b.hashCode() gelten!

    @Override
    public int hashCode()
    {
        return _euro ^ _cent;
    }

    public Geldbetrag addiere(Geldbetrag that)
    {
        int eurocent = that._eurocent;
        int ergebnis = _eurocent + eurocent;
        
        return new Geldbetrag(ergebnis);
    }

    public Geldbetrag minus(Geldbetrag that)
    {
        int eurocent = that._eurocent;
        int ergebnis = _eurocent - eurocent;
        
        return new Geldbetrag(ergebnis);
    }

    public static Geldbetrag valueOf(String string)
    {
        Matcher matcher = regex.matcher(string);
        if (matcher.matches())
        {
            String euro = matcher.group(1);
            String cent = matcher.group(2);
            // TODO 0,01 =|= 0,1
            return Geldbetrag.valueOf(Integer.valueOf(euro),
                    Integer.valueOf(cent));
        }
        throw new IllegalArgumentException(matcher.toString());
    }

    private static final Pattern regex = Pattern.compile("(\\d+),(\\d?{2,})");

    @Override
    public int compareTo(Geldbetrag that)
    {
        if (this._eurocent < that._eurocent) return -1;
        if (this._eurocent > that._eurocent) return +1;
        return 0;
    }
}
