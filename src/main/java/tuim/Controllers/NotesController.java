package tuim.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;
import tuim.POJO.VoiceNotePOJO;
import tuim.Repository.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Radek on 12.06.2017.
 */


@RestController
@RequestMapping("/notes")

public class NotesController {

    @Autowired
    NotesRepository notesRepository;

/*
    @GetMapping("/archive")
    public ArrayList<InsurancePOJO> getArchive() {
        return notesRepository.getInsuranceArchive();
    }
*/


    @PostMapping("/note")
    public ResponseEntity addNote(@RequestBody VoiceNotePOJO voiceNotePOJO) {
        try {
            notesRepository.addNote(voiceNotePOJO);
        } catch (IOException e) {
            e.printStackTrace();
        }

/*        try {
            notesRepository.addInsurance(insurancePOJO);
        } catch (AlreadyExistException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.CREATED);*/
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/notes")
    public ArrayList<VoiceNotePOJO> getNotes(){
        ArrayList<VoiceNotePOJO> result = new ArrayList<>();

        File folder = new File("notatki");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()){

                    int size = (int) listOfFiles[i].length();

                    byte[] bytes = new byte[size];

                    try {
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(listOfFiles[i]));
                        bis.read(bytes, 0, bytes.length);
                        bis.close();

                   result.add(new VoiceNotePOJO(bytes, listOfFiles[i].getName()));

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }


        }
    return result;
    }
/*

    @GetMapping("/car/{tablePlate}")
    public ResponseEntity getInsurance(@PathVariable String tablePlate){
        try {
            if(notesRepository.getInsurance(tablePlate)!= null)
                return new ResponseEntity<>(notesRepository.getInsurance(tablePlate), HttpStatus.OK);
            else
                return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (MovedToArchiveException e) {
            return new ResponseEntity(HttpStatus.GONE);
        }
    }

*/

}

