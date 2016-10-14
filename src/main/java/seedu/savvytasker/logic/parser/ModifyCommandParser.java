package seedu.savvytasker.logic.parser;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.savvytasker.commons.core.Messages;
import seedu.savvytasker.logic.commands.ModifyCommand;
import seedu.savvytasker.logic.commands.models.ModifyCommandModel;
import seedu.savvytasker.logic.parser.DateParser.InferredDate;
import seedu.savvytasker.model.task.PriorityLevel;
import seedu.savvytasker.model.task.RecurrenceType;

public class ModifyCommandParser implements CommandParser<ModifyCommand> {
    private static final String HEADER = "modify";
    private static final String READABLE_FORMAT = "modify INDEX [t/TASK_NAME] [s/START_DATE] "
            + "[st/START_TIME] [e/END_DATE] [et/END_TIME] [l/LOCATION] [p/PRIORITY_LEVEL] "
            + "[r/RECURRING_TYPE] [n/NUMBER_OF_RECURRENCE] [c/CATEGORY] [d/DESCRIPTION]";

    private static final String REGEX_REF_INDEX = "Index";
    private static final String REGEX_REF_TASK_NAME = "TaskName";
    private static final String REGEX_REF_START_DATE = "StartDate";
    private static final String REGEX_REF_START_TIME = "StartTime";
    private static final String REGEX_REF_END_DATE = "EndDate";
    private static final String REGEX_REF_END_TIME = "EndTime";
    private static final String REGEX_REF_LOCATION = "Location";
    private static final String REGEX_REF_PRIORITY_LEVEL = "Priority";
    private static final String REGEX_REF_RECURRING_TYPE = "RecurringType";
    private static final String REGEX_REF_NUMBER_OF_RECURRENCE = "RecurringNumber";
    private static final String REGEX_REF_CATEGORY = "Category";
    private static final String REGEX_REF_DESCRIPTION = "Description";
    
    private static final Pattern REGEX_PATTERN = Pattern.compile(
            "modify\\s+(?<"+REGEX_REF_INDEX+">([^/]+?(\\s+|$))+)((?<=\\s)(" +
            "(t/(?<"+REGEX_REF_TASK_NAME+">[^/]+)(?!.*\\st/))|" +
            "(s/(?<"+REGEX_REF_START_DATE+">[^/]+)(?!.*\\ss/))|" +
            "(st/(?<"+REGEX_REF_START_TIME+">[^/]+)(?!.*\\sst/))|" +
            "(e/(?<"+REGEX_REF_END_DATE+">[^/]+)(?!.*\\se/))|" +
            "(et/(?<"+REGEX_REF_END_TIME+">[^/]+)(?!.*\\set/))|" +
            "(l/(?<"+REGEX_REF_LOCATION+">[^/]+)(?!.*\\sl/))|" +
            "(p/(?<"+REGEX_REF_PRIORITY_LEVEL+">[^/]+)(?!.*\\sp/))|" +
            "(r/(?<"+REGEX_REF_RECURRING_TYPE+">[^/]+)(?!.*\\sr/))|" +
            "(n/(?<"+REGEX_REF_NUMBER_OF_RECURRENCE+">[^/]+?)(?!.*\\sn/))|" +
            "(c/(?<"+REGEX_REF_CATEGORY+">[^/]+)(?!.*\\sc/))|" +
            "(d/(?<"+REGEX_REF_DESCRIPTION+">[^/]+)(?!.*\\sd/))" +
            ")(\\s|$)){0,11}", Pattern.CASE_INSENSITIVE);

    private static final TaskFieldParser TASK_PARSER = new TaskFieldParser();
    private static final IndexParser INDEX_PARSER = new IndexParser();
    
    @Override
    public String getHeader() {
        return HEADER;
    }

    @Override
    public String getRequiredFormat() {
        return READABLE_FORMAT;
    }

    @Override
    public ModifyCommand parse(String commandText) throws ParseException {
        Matcher matcher = REGEX_PATTERN.matcher(commandText);
        if (matcher.matches()) {

            int index = parseIndex(matcher.group(REGEX_REF_INDEX));
            InferredDate startDate = TASK_PARSER.parseStartDate(matcher.group(REGEX_REF_START_DATE));
            InferredDate endDate = TASK_PARSER.parseEndDate(matcher.group(REGEX_REF_END_DATE));
            String taskName = TASK_PARSER.parseTaskName(matcher.group(REGEX_REF_TASK_NAME));
            String location = TASK_PARSER.parseLocation(matcher.group(REGEX_REF_LOCATION));
            PriorityLevel priority = TASK_PARSER.parsePriorityLevel(matcher.group(REGEX_REF_PRIORITY_LEVEL));
            RecurrenceType recurrence = TASK_PARSER.parseRecurrenceType(matcher.group(REGEX_REF_RECURRING_TYPE));
            Integer nrOfRecurrence = TASK_PARSER.parseNumberOfRecurrence(matcher.group(REGEX_REF_NUMBER_OF_RECURRENCE));
            String category = TASK_PARSER.parseCategory(matcher.group(REGEX_REF_CATEGORY));
            String description = TASK_PARSER.parseDescription(matcher.group(REGEX_REF_DESCRIPTION));
            
               
            // TODO: Create ModifyCommand here (require integration)
            Date startDateTime = null, endDateTime = null;
            int numberOfRecurrence = 0;
            if (nrOfRecurrence != null) numberOfRecurrence = nrOfRecurrence.intValue();
            else numberOfRecurrence = ModifyCommandModel.UNINITIALIZED_NR_RECURRENCE_VALUE;
            if (startDate != null) startDateTime = startDate.getInferredDateTime();
            if (endDate != null) endDateTime = endDate.getInferredDateTime();
            return new ModifyCommand(
                    new ModifyCommandModel(index, taskName, startDateTime, 
                            endDateTime, location, priority, 
                            recurrence, numberOfRecurrence, 
                            category, description));
        }
        
        throw new ParseException(commandText, String.format(
                Messages.MESSAGE_INVALID_COMMAND_FORMAT, getRequiredFormat()));
    }

    private int parseIndex(String indexText) throws ParseException {
        try {
            return INDEX_PARSER.parseSingle(indexText);
        } catch (ParseException ex) {
            throw new ParseException(indexText, "INDEX: " + ex.getFailureDetails());
        }
    }
}
