package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;

/**
 * Deletes an event identified using it's displayed index from the address book.
 */
public class DeleteEventCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the event identified by the index number used in the displayed event list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + "Tutorial/1";

    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted Event: %1$s";

    private final Index targetIndex;

    private boolean isTutorial;
    private boolean isLab;
    private boolean isConsultation;

    /**
     * Sets all the boolean flags (isTutorial, isLab, and isConsultation) to false
     *
     * @param targetIndex
     */
    public DeleteEventCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        isTutorial = false;
        isLab = false;
        isConsultation = false;
    }

    /**
     * Marks the tutorial as true and the rest as false
     */
    public void markTutorial() {
        isTutorial = true;
        isLab = false;
        isConsultation = false;
    }

    /**
     * Marks the lab as true and the rest as false
     */
    public void markLab() {
        isLab = true;
        //ensures no mishandling of cases
        isTutorial = false;
        isConsultation = false;
    }

    /**
     * Marks the consultation as true and the rest as false
     */
    public void markConsultation() {
        isConsultation = true;
        //ensures no mishandling of cases
        isLab = false;
        isTutorial = false;
    }

    /**
     * Executes the deletion based on whether it is a tutorial, lab or a consultation
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     * @throws CommandException if the command is invalid
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (isTutorial) {
            return executeTutorial(model);
        } else if (isLab) {
            return executeLab(model);
        } else {
            return executeConsultation(model);
        }
    }

    /**
     * Deletes the index from the list of most recent filtered tutorial list
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     * @throws CommandException if the command is invalid
     */
    public CommandResult executeTutorial(Model model) throws CommandException {

        List<Tutorial> lastShownList = model.getFilteredTutorialList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Tutorial tutorialToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTutorial(tutorialToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, tutorialToDelete));
    }

    /**
     * Deletes the index from the list of most recent filtered lab list
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     * @throws CommandException if the command is invalid
     */
    public CommandResult executeLab(Model model) throws CommandException {

        List<Lab> lastShownList = model.getFilteredLabList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Lab labToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteLab(labToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, labToDelete));
    }

    /**
     * Deletes the index from the list of most recent filtered consultation list
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     * @throws CommandException if the command is invalid
     */
    public CommandResult executeConsultation(Model model) throws CommandException {

        List<Consultation> lastShownList = model.getFilteredConsultationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Consultation consultationToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteConsultation(consultationToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, consultationToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteEventCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteEventCommand) other).targetIndex)); // state check
    }
}