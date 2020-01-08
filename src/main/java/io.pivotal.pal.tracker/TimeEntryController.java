package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    //TimeEntryRepository tep=new InMemoryTimeEntryRepository();

    TimeEntryRepository tep;

    public TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.tep=timeEntryRepository;

    }
    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntry){

        return ResponseEntity.status(HttpStatus.CREATED).body(tep.create(timeEntry));
    }
    @GetMapping("{id}")
    public ResponseEntity read(@PathVariable Long id){
        TimeEntry found_TE = tep.find(id);
        if (found_TE == null) return new ResponseEntity<>(found_TE, HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(found_TE, HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id,@RequestBody TimeEntry te){

        TimeEntry updated_TE = tep.update(id, te);
        if (updated_TE == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated_TE, HttpStatus.OK);
       /* if(tep.find(id)!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(tep.update(id, te));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
*/
    }

    @GetMapping
    public ResponseEntity list(){
        return ResponseEntity.status(HttpStatus.OK).body(tep.list());
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        //if(tep.find(id)!=null) {
            tep.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

       // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


}
