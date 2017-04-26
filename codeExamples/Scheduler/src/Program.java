import java.util.LinkedList;
import java.util.List;

// EBNF
//
// scenario = process , { process } ;
// process = name , ':' , workplan , ';' ;  
// workplan = initphase , { ',' , phase } ;
// initphase = work, { ',' , launch };
// phase = [ wait , ',' ], work, { ',' , launch };
// name = ? Ein großer Buchstabe des lateinischen Alphabets A-Z ? ,['(' , number , ')'] ;
// wait = 'Wait(' , number , ')' ;
// work = 'Work(' , number , ')' ;
// launch = 'Launch(' , name , ')' ;
// number = digit , { digit } ;
// digit = '0' | '1' | '2' | '3' |  '4' | '5' | '6' |  '7' | '8' | '9';
//

public class Program {
    private String name;
    private List<Section> workplan = new LinkedList<Section>();

    public Program(String name) {
        this.name = name;
    }

    public void addSection(Section s) {
        if (s != null) {
            workplan.add(s);
        }
    }

    @Override
    public String toString() {
        String ps = "";
        for (Section s : workplan) {
            if (ps.isEmpty()) {
                ps = s.toString();
            } else {
                ps += "," + s.toString();
            }
        }
        return ps;
    }

    public String getName() {
        return name;
    }

    public List<Section> getWorkplan() {
        return workplan;
    }

    public Section getSection(int i) {
        return workplan.get(i);
    }

    public int getSectionDuration(int i) {
        return workplan.get(i).getDuration();
    }

    public boolean isLastSection(int i) {
        return workplan.size() == i + 1;
    }

}
