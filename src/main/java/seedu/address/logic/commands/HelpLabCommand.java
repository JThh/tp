package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Help instruction for Lab command
 */
public class HelpLabCommand extends HelpCommand {

    public static final String COMMAND_WORD = "help lab";

    public static final String HEADER_1 = "----- Labs Basic Inputs -----\n";

    public static final String HEADER_2 = "\n----- Student Inputs -----\n";

    public static final String HEADER_3 = "\n----- Note Inputs -----\n";

    public static final String ADD_LAB = "Add:                         "
            + "vim Lab/LAB_NAME";

    public static final String ADD_RECUR = "Add Multiple:            "
            + "schedule Recur/Lab/LAB_NAME -n REPETITIONS";

    public static final String DELETE_LAB = "Delete:                     "
            + "delete Lab/INDEX";

    public static final String EDIT_LAB = "Edit:                         "
            + "editEvent EVENT_INDEX Lab/NEW_NAME -date NEW_DATE -file NEW_FILEPATH";

    public static final String ADD_STUDENT = "Add Student:            "
            + "addStudent STUDENT_INDEX_IN_LIST Lab/LAB_INDEX_IN_LAB_LIST";

    public static final String DELETE_STUDENT = "Delete Student:        "
            + "deleteStudent STUDENT_INDEX_IN_LIST Lab/LAB_INDEX_IN_LAB_LIST";

    public static final String ADD_NOTE = "Add Note:         "
            + "add-note -content NOTE ?????";

    public static final String EDIT_NOTE = "Edit Note:          "
            + "edit-note -content NOTE ?????";

    public static final String DELETE_NOTE = "Delete Note:      "
            + "rm-note -type Lab -name STUDENT_NAME_OR_INDEX -index NOTE_INDEX";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HEADER_1
                + ADD_LAB + "\n"
                + ADD_RECUR + "\n"
                + DELETE_LAB + "\n"
                + EDIT_LAB + "\n"
                + HEADER_2
                + ADD_STUDENT + "\n"
                + DELETE_STUDENT + "\n"
                + HEADER_3
                + ADD_NOTE + "\n"
                + DELETE_NOTE + "\n"
                + EDIT_NOTE);
    }
}
