package seedu.savvytasker.model.person;

import java.util.Date;
import java.util.Objects;

import seedu.savvytasker.logic.commands.models.ModifyCommandModel;
import seedu.savvytasker.model.task.PriorityLevel;
import seedu.savvytasker.model.task.RecurrenceType;

/**
 * Represents a Task in the task list.
 */
public class Task implements ReadOnlyTask {

    private String taskName;
    private Date startDateTime;
    private Date endDateTime;
    private String location;
    private PriorityLevel priority;
    private RecurrenceType recurringType;
    private int numberOfRecurrence;
    private String category;
    private String description;

    public Task(String taskName, Date startDateTime, Date endDateTime, String location,
            PriorityLevel priority, RecurrenceType recurringType, int numberOfRecurrence, 
            String category, String description) {
        this.taskName = taskName;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.location = location;
        this.priority = priority;
        this.recurringType = recurringType;
        this.numberOfRecurrence = numberOfRecurrence;
        this.category = category;
        this.description = description;
    }
    
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
        this(source.getTaskName(), source.getStartDateTime(), source.getEndDateTime(), 
                source.getLocation(), source.getPriority(), source.getRecurringType(),
                source.getNumberOfRecurrence(), source.getCategory(), source.getDescription());
    }

    /**
     * Copy and modify constructor
     */
    public Task(ReadOnlyTask source, ModifyCommandModel commandModel) {
        this(source.getTaskName(), source.getStartDateTime(), source.getEndDateTime(), 
                source.getLocation(), source.getPriority(), source.getRecurringType(),
                source.getNumberOfRecurrence(), source.getCategory(), source.getDescription());
        
        this.taskName = commandModel.getTaskName() == null ? this.taskName : commandModel.getTaskName();
        this.startDateTime = commandModel.getStartDateTime() == null ? this.startDateTime : commandModel.getStartDateTime();
        this.endDateTime = commandModel.getEndDateTime() == null ? this.endDateTime : commandModel.getEndDateTime();
        this.location = commandModel.getLocation() == null ? this.location : commandModel.getLocation();
        this.priority = commandModel.getPriority() == null ? this.priority : commandModel.getPriority();
        this.recurringType = commandModel.getRecurringType() == null ? this.recurringType : commandModel.getRecurringType();
        this.numberOfRecurrence = commandModel.getNumberOfRecurrence() == ModifyCommandModel.UNINITIALIZED_NR_RECURRENCE_VALUE ? 
                this.numberOfRecurrence : commandModel.getNumberOfRecurrence();
        this.category = commandModel.getCategory() == null ? this.category : commandModel.getCategory();
        this.description = commandModel.getDescription() == null ? this.description : commandModel.getDescription();
    }

    @Override
    public String getTaskName() {
        return taskName;
    }

    @Override
    public Date getStartDateTime() {
        return startDateTime;
    }

    @Override
    public Date getEndDateTime() {
        return endDateTime;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public PriorityLevel getPriority() {
        return priority;
    }

    @Override
    public RecurrenceType getRecurringType() {
        return recurringType;
    }

    @Override
    public int getNumberOfRecurrence() {
        return numberOfRecurrence;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getDescription() {
        return description;
    }
    
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPriority(PriorityLevel priority) {
        this.priority = priority;
    }

    public void setRecurringType(RecurrenceType recurringType) {
        this.recurringType = recurringType;
    }

    public void setNumberOfRecurrence(int numberOfRecurrence) {
        this.numberOfRecurrence = numberOfRecurrence;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskName);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}