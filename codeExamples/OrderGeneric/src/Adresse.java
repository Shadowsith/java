public class Adresse implements Comparable<Adresse> {
  String ort;
  int plz;
  String strasse;

  public Adresse(String ort, int plz, String strasse) {
    this.ort = ort;
    this.plz = plz;
    this.strasse = strasse;
  }

  public int compareTo(Adresse other) {
    return compareToV2(other);
  }

  public int compareToV1(Adresse other) {
    // Festlegung: Reihung aufsteigend nach Postleitzahl
    // Ganz explizit und umstaendlich
    if (this.plz < other.plz) {
      return -17; // Wert muss negativ sein; ansonsten beliebig
    } else if (this.plz > other.plz) {
      return +42; // Wert muss positiv sein; ansonsten beliebig
    } else {
      return 0;
    }
  }

  public int compareToV2(Adresse other) {
    // Nach PLZ vergleichen ueber 'logische Differenz'
    return plz - other.plz;
  }

  public String getOrt() {
    return ort;
  }

  public void setOrt(String ort) {
    this.ort = ort;
  }

  public int getPlz() {
    return plz;
  }

  public void setPlz(int plz) {
    this.plz = plz;
  }

  public String getStrasse() {
    return strasse;
  }

  public void setStrasse(String strasse) {
    this.strasse = strasse;
  }

  @Override
  public String toString() {
    return strasse + "; " + plz + " " + ort;
  }
}
