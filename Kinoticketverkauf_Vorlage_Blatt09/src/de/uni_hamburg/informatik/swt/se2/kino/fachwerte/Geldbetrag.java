package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

/**
 * Eine Klasse, um Geldbeträge benutzen zu können, die nur das können sollen, was Geld auch kann.
 * 
 * @author Utz
 *
 */
public final class Geldbetrag implements Comparable<Geldbetrag>
{
    private final int _euro;
    private final int _cent;
    private final int _eurocent;

    private static final Pattern regex = Pattern.compile("(\\d{0,8})(,(\\d?\\d?))?");

    /**
     * Initialisiert einen Geldbetrag.
     * 
     * @param euro Der Euroanteil des Geldbetrags
     * @param cent Der Centanteil des Geldbetrags
     */
    private Geldbetrag(int euro, int cent)
    {
        if (euro >= 0)
        {
            _euro = euro;
            _cent = cent;
            _eurocent = (euro * 100) + cent;
        }
        else
        {
            _euro = euro;
            _cent = -cent;
            _eurocent = (euro * 100) - cent;
        }
    }

    /**
     * Initialisiert einen Geldbetrag.
     * 
     * @param eurocent Der Gesamtwert des Geldbetrags in Eurocent
     */
    private Geldbetrag(int eurocent)
    {
        if (eurocent >= 0)
        {
            _euro = (int) (eurocent / 100);
            _cent = eurocent % 100;
            _eurocent = eurocent;
        }
        else
        {
            _euro = (int) (eurocent / 100);
            _cent = -eurocent % 100;
            _eurocent = eurocent;
        }
    }

    /**
     * Erstellt einen neuen Geldbetrag aus einem gegebenen Euro- und Centwert.
     * 
     * @param euro Der Eurowert des neuen Geldbetrages
     * @param cent Der Centwert des neuen Geldbetrages
     * 
     * @require cent Der Centbetrag muss kleiner als 100 sein.
     * @require cent Der Centbetrag muss größer gleich 0 sein.
     * 
     * @return Der neu entstandene Geldbetrag
     */
    public static Geldbetrag valueOf(int euro, int cent)
    {
        assert cent<100 : "Centbetrag falsch";
        assert cent>=0 : "Centbetrag falsch";
        
        return new Geldbetrag(euro, cent);
    }

    /**
     * Erstellt einen neuen Geldbetrag aus einem gegebenen Eurocentwert.
     * 
     * @param eurocent Der Gesamtwert des Geldbetrags in Eurocent
     * 
     * @return Der neu entstandene Geldbetrag
     */
    public static Geldbetrag valueOf(int eurocent)
    {
        return new Geldbetrag(eurocent);
    }

    /**
     * Versucht, aus einen String einen Geldbetrag zu machen.
     * Sollte dies fehl schlagen, wird eine Fehlermeldung aufgerufen.
     * 
     * @param string Die Eingabe, die mal ein Geldbetrag werden will
     * 
     * @require Der übergebene String darf nicht null sein.
     * 
     * @return Der fertige Geldbetrag
     */
    public static Geldbetrag valueOf(String string)
    {
        assert string != null : "Vorbedingung verletzt: null";
        
        Matcher matcher = regex.matcher(string);
        if (matcher.matches())
        {
            int euro = Integer.valueOf(matcher.group(1));
            int cent;
            if (matcher.group(3) != null && !matcher.group(3)
                .isEmpty())
                cent = Integer.valueOf(matcher.group(3));
            else
                cent = 0;

            // TODO CHECK 0,01 =|= 0,1
            if (string.contains(","))
            {
                Pattern regex2 = Pattern.compile(",([1-9])");
                Matcher matcher2 = regex2.matcher(string.substring(string.indexOf(',')));
                if (matcher2.matches())
                {
                    cent *= 10;
                }
            }
            int eurocent = (euro * 100) + cent;

            return Geldbetrag.valueOf(eurocent);
        }
        throw new NumberFormatException(matcher.toString());
    }

    /**
     * Gibt den aktuellen Eurowert des Geldbetrages wieder.
     * 
     * @return Der aktuelle Eurowert
     */
    public int euro()
    {
        return _euro;
    }

    /**
     * Gibt den aktuellen Centwert des Geldbetrages wieder.
     * 
     * @return Der aktuelle Centwert
     */
    public int cent()
    {
        return _cent;
    }

    /**
     * Gibt den aktuellen Eurocentwert des Geldbetrages wieder.
     * 
     * @return Der aktuelle Eurocentwert
     */
    public int eurocent()
    {
        return _eurocent;
    }

    /**
     * Multipliziert den Geldbetrag mit einem Faktor.
     * 
     * @param faktor Der Faktor, mit dem der Geldbetrag multipliziert wird
     * 
     * @return Der neu entstandene Geldbetrag
     */
    public Geldbetrag multipliziere(int faktor)
    {
        int euro;
        int cent = _cent * faktor;
        if (cent > 99)
        {
            euro = _euro * faktor + (int) (cent / 100);
            cent = cent % 100;
        }
        else
        {
            euro = _euro * faktor;
        }
        return new Geldbetrag(euro, cent);
    }

    /**
     * Addiert einen anderen Gedbetrag auf den aktuellen Geldbetrag.
     * 
     * @param that Der aufzuaddierende Geldbetrag
     * 
     * @require Der Geldbetrag muss existieren.
     * 
     * @return Der neu entstandene Geldbetrag
     */
    public Geldbetrag addiere(Geldbetrag that)
    {
        assert that != null : "Vorbedingung verletzt: null";
        
        int ergebnis = this._eurocent + that._eurocent;
        return new Geldbetrag(ergebnis);
        
    }

    /**
     * Subtrahiert einen anderen Gedbetrag von dem aktuellen Geldbetrag.
     * 
     * @param that Der zu subtrahierende Geldbetrag
     * 
     * @require Der Geldbetrag muss existieren.
     * 
     * @return Der neu entstandene Geldbetrag
     */
    public Geldbetrag subtrahiere(Geldbetrag that)
    {
        assert that != null : "Vorbedingung verletzt: null";
        
        int ergebnis = this._eurocent - that._eurocent;

        return new Geldbetrag(ergebnis);
    }

    /**
     * Gibt den Wert des Geldbetrages, unabhängig vom Vorzeichen aus.
     * 
     * @return Der Wert des Geldbetrages, unabhängig vom Vorzeichen
     */
    public Geldbetrag betrag()
    {
        if (_eurocent < 0) return new Geldbetrag(-_eurocent);
        return new Geldbetrag(_eurocent);
    }

    /**
     * Gibt einen String aus, der dem Geldbetrag entspricht. 
     * Bewahrt dabei das Format mit zwei Centstellen.
     * 
     * @return Den Geldbetrag als String
     */
    public String toString()
    {
        if (_cent > 9)
            return _euro + "," + _cent;
        else if (_cent > 0) return _euro + ",0" + _cent;
        return _euro + ",00";
    }

    /**
     * Prüft, ob zwei Geldbetraege identisch sind (objektbezogen).
     * 
     * @param Das zu identifizierende Objekt.
     * 
     * @require Das Objekt muss existieren.
     */
    @Override
    public boolean equals(Object obj)
    {
        assert obj != null : "Vorbedingung verletzt: null";
        
        return (obj instanceof Geldbetrag) && equals((Geldbetrag) obj);
    }

    /**
     * Prüft, ob zwei Geldbetraege identisch sind.
     * 
     * @param Der zu identifizierende Geldbetrag.
     * 
     * @require Der Geldbetrag muss existieren.
     * 
     */
    public boolean equals(Geldbetrag that)
    {
        assert that != null : "Vorbedingung verletzt: null";
        
        return this._eurocent == that._eurocent;
    }

    // Wenn a.equals(b) gilt, dann muss auch a.hashCode() == b.hashCode() gelten!

    /**
     * Erstellt einen HashCode fuer einen Geldbetrag, sodass der gleiche Geldbetrag 
     * auch den gleichen HashCode aufweist.
     * 
     */
    @Override
    public int hashCode()
    {
        return _euro ^ _cent;
    }

    /**
     * Vergleicht diesen Gledbetrag mit einem anderen Geldbetrag, der uebergeben wird. 
     * 
     * @param Der zu vergleichende Geldbetrag.
     * 
     * @require Der Geldbetrag muss existieren.
     * 
     * @return Int, der einen Vergleichswert angibt.
     */
    @Override
    public int compareTo(Geldbetrag that)
    {
        assert that != null : "Vorbedingung verletzt: null";
        
        if (this._eurocent < that._eurocent) return -1;
        if (this._eurocent > that._eurocent) return +1;
        return 0;
    }
}
