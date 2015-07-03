package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Geldbetrag implements Comparable<Geldbetrag>
{
    private final int _euro;
    private final int _cent;
    private final int _eurocent;

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

    public static Geldbetrag valueOf(int euro, int cent)
    {
        return new Geldbetrag(euro, cent);
    }

    public static Geldbetrag valueOf(int eurocent)
    {
        return new Geldbetrag(eurocent);
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
        if (_cent > 9)
            return _euro + "," + _cent;
        else if (_cent > 0) return _euro + ",0" + _cent;
        return _euro + ",00";
    }

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
        int euro;
        int cent;
        int eurocent;

        if (str.contains(","))
        {
            euro = Integer.parseInt(str.substring(0, str.indexOf(',')));
            cent = Integer.parseInt(str.substring(str.indexOf(',') + 1));
            eurocent = (euro * 100) + cent;
        }
        else
        {
            euro = Integer.parseInt(str);
            cent = 0;
            eurocent = (euro * 100) + cent;
        }

        return new Geldbetrag(eurocent);
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
            int euro = Integer.valueOf(matcher.group(1));
            int cent;
            if (!matcher.group(2)
                .isEmpty())
                cent = Integer.valueOf(matcher.group(2));
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
        fehler();
        throw new NumberFormatException(matcher.toString());
    }

    private static void fehler()
    {
        JOptionPane error = new JOptionPane(JOptionPane.OK_OPTION);
        JOptionPane.showMessageDialog(error,
                "Bitte nur sinnvolle Geldbeträge eingeben.", "Warnung",
                JOptionPane.WARNING_MESSAGE);
        error.setVisible(true);
    }

    private static final Pattern regex = Pattern.compile("(\\d+),?(\\d?\\d?)");

    @Override
    public int compareTo(Geldbetrag that)
    {
        if (this._eurocent < that._eurocent) return -1;
        if (this._eurocent > that._eurocent) return +1;
        return 0;
    }

    public Geldbetrag betrag()
    {
        if (_eurocent < 0) return new Geldbetrag(-_eurocent);
        return new Geldbetrag(_eurocent);
    }
}
