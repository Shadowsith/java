// RowModel for example TableRowAddDeleteDemo.java
// Author: FABR

public class RowModel {
    private static int lastId = 0; // Class variable

    private Integer id;
    private String firstname, lastname, sport;
    private Integer numyears;
    private Boolean isvegi;

    public RowModel(String firstname, String lastname, String sport,
            Integer numyears, Boolean isvegi) {
        super();
        this.id = this.lastId++;

        this.firstname = firstname;
        this.lastname = lastname;
        this.sport = sport;
        this.numyears = numyears;
        this.isvegi = isvegi;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public Integer getNumyears() {
        return numyears;
    }

    public void setNumyears(Integer numyears) {
        this.numyears = numyears;
    }

    public Boolean getIsvegi() {
        return isvegi;
    }

    public void setIsvegi(Boolean isvegi) {
        this.isvegi = isvegi;
    }

    @Override
    public String toString() {
        return "  " + id + "  " + firstname + "  " + lastname + "  " + sport
                + "  " + numyears + "  " + isvegi;
    }
}
