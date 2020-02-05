package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository{

   public TimeEntry create(TimeEntry te);

    public TimeEntry find(Long Id);

    public TimeEntry update(Long Id,TimeEntry te);

    public void delete(Long id);

    public List<TimeEntry> list();

}
