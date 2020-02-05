package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InMemoryTimeEntryRepository implements TimeEntryRepository  {

    private static Map<Long, TimeEntry> teMap = new HashMap<Long, TimeEntry>();
    private static Long idIndex = 0L;
/* static {
        TimeEntry a = new TimeEntry(1L, 100L, 101L, LocalDate.parse("2020-01-08") , 10);
        teMap.put(1L, a);
        TimeEntry b = new TimeEntry(2L, 200L, 201L,LocalDate.parse("2020-01-08") , 20);
        teMap.put(2L, b);
        TimeEntry c = new TimeEntry(3L, 300L, 301L, LocalDate.parse("2020-01-08"), 30);
        teMap.put(3L, c);
    }*/
public InMemoryTimeEntryRepository(){

}
    public TimeEntry create(TimeEntry te){
        ++idIndex;
         TimeEntry te_new= new TimeEntry(idIndex,te.getProjectId(),te.getUserId(),te.getDate(),te.getHours());
        teMap.put(idIndex, te_new);
        return te_new;
    }

    public TimeEntry find(Long Id){
        return teMap.get(Id);
    }

    public TimeEntry update(Long id,TimeEntry te){
    if(teMap.get(id)!=null) {
        TimeEntry te_new = teMap.get(id);
        te_new.setId(id);
        te_new.setProjectId(te.getProjectId());
        te_new.setUserId(te.getUserId());
        te_new.setDate(te.getDate());
        te_new.setHours(te.getHours());
        teMap.put(id, te_new);
        return te_new;
    }
        return teMap.get(id);
    }

    public List<TimeEntry> list(){
        return new ArrayList<TimeEntry>(teMap.values());
    }

    public void delete(Long id){
        teMap.remove(id);
    }
}
