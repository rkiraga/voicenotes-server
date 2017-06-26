package tuim.POJO;

/**
 * Created by Radek2 on 23.06.2017.
 */
public class VoiceNotePOJO {

    byte[] file;
    String name;

    public VoiceNotePOJO(byte[] file, String description) {
        this.file = file;
        this.name = description;
    }

    public VoiceNotePOJO() {
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
