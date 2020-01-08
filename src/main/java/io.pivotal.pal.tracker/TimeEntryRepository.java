package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository{

    TimeEntry create(TimeEntry te);

    TimeEntry find(Long Id);

    TimeEntry update(Long Id,TimeEntry te);

    void delete(Long Id);

    List<TimeEntry> list();

}
