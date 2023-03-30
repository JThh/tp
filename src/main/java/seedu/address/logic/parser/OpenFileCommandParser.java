package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERFORMANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHOTO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.OpenFileCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new OpenFileCommand object
 */
public class OpenFileCommandParser implements Parser<OpenFileCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the OpenFileCommand
     * and returns a OpenFileCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public OpenFileCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenizeFirstPrefix(args, PREFIX_TUTORIAL, PREFIX_LAB, PREFIX_CONSULTATION);

        //Make the user not create tutorial and students with the same command
        if (!arePrefixesAbsent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_PHOTO, PREFIX_DATE, PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_PERFORMANCE,
                PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    OpenFileCommand.MESSAGE_USAGE));
        }

        if ((!(arePrefixesPresent(argMultimap, PREFIX_LAB)
                || arePrefixesPresent(argMultimap, PREFIX_CONSULTATION)
                || arePrefixesPresent(argMultimap, PREFIX_TUTORIAL))
                || !argMultimap.getPreamble().isEmpty())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    OpenFileCommand.MESSAGE_USAGE));
        }

        Index index;
        OpenFileCommand openFileCommand;

        if (argMultimap.getValue(PREFIX_CONSULTATION).isPresent()) {
            throw new ParseException("Consultation events do not have files attached to them");
        }

        if (argMultimap.getValue(PREFIX_TUTORIAL).isPresent()) {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TUTORIAL).get());
            openFileCommand = new OpenFileCommand(index);
            openFileCommand.markTutorial();
        } else {
            //If it is not tutorial and not a consultation, it has to be a lab
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_LAB).get());
            openFileCommand = new OpenFileCommand(index);
            openFileCommand.markLab();
        }
        return openFileCommand;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if none of the prefixes contains command to add students (cannot add student and tutorial
     * using the same command.)
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesAbsent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).noneMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}