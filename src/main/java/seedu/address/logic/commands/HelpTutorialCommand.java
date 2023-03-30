package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Help instruction for Tutorial command
 */
public class HelpTutorialCommand extends HelpCommand {

    public static final String COMMAND_WORD = "help tutorial";

    public static final String HEADER_1 = "----- Tutorial Basic Inputs -----\n";

    public static final String HEADER_2 = "\n----- Student Inputs -----\n";

    public static final String HEADER_3 = "\n----- Note Inputs -----\n";

    public static final String ADD_TUTORIAL = "Add:                         "
            + "touch Tutorial/TUTORIAL_NAME";

    public static final String ADD_RECUR = "Add Multiple:            "
            + "schedule Recur/Tutorial/TUTORIAL_NAME -n REPETITIONS";

    public static final String DELETE_TUTORIAL = "Delete:                     "
            + "delete Tutorial/INDEX";

    public static final String EDIT_TUTORIAL = "Edit:                         "
            + "editEvent EVENT_INDEX Tutorial/NEW_NAME -date NEW_DATE -file NEW_FILEPATH";

    public static final String ADD_STUDENT = "Add Student:            "
            + "addStudent STUDENT_INDEX_IN_LIST Tutorial/TUTORIAL_INDEX_IN_TUTORIAL_LIST";

    public static final String DELETE_STUDENT = "Delete Student:        "
            + "deleteStudent STUDENT_INDEX_IN_LIST Tutorial/TUTORIAL_INDEX_IN_TUTORIAL_LIST";

    public static final String ADD_NOTE = "Add Note:         "
            + "add-note -content NOTE ?????";

    public static final String EDIT_NOTE = "Edit Note:          "
            + "edit-note -content NOTE ?????";

    public static final String DELETE_NOTE = "Delete Note:      "
            + "rm-note -type Tutorial -name STUDENT_NAME_OR_INDEX -index NOTE_INDEX";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HEADER_1
                + ADD_TUTORIAL + "\n"
                + ADD_RECUR + "\n"
                + DELETE_TUTORIAL + "\n"
                + EDIT_TUTORIAL + "\n"
                + HEADER_2
                + ADD_STUDENT + "\n"
                + DELETE_STUDENT + "\n"
                + HEADER_3
                + ADD_NOTE + "\n"
                + DELETE_NOTE + "\n"
                + EDIT_NOTE);
    }
}
