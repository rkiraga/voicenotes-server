package tuim.Repository;

/**
 * Created by Radek on 12.06.2017.
 */

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tuim.POJO.VoiceNotePOJO;


import javax.annotation.PostConstruct;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
@Scope("singleton")
public class NotesRepository {


    @PostConstruct
    private void init() {
    }


    public void addNote(VoiceNotePOJO voiceNotePOJO) throws IOException {
        String name = voiceNotePOJO.getName().replaceAll("\\W+","_");
        name = name.replace("_3gp", ".3gp");
        FileOutputStream stream = new FileOutputStream("notatki/"+name);
        try {
            stream.write(voiceNotePOJO.getFile());
        } finally {
            stream.close();
        }
    }


}