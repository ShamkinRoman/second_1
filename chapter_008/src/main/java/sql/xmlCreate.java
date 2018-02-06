package sql;

import com.sun.xml.internal.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class xmlCreate {

    private String entry;
    private int field;



    public int getField() {
        return field;
    }

    @XmlElement
    public void setField(int field) {
        this.field = field;
    }
}
