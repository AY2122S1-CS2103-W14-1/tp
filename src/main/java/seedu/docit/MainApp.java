package seedu.docit;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.docit.commons.core.Config;
import seedu.docit.commons.core.LogsCenter;
import seedu.docit.commons.core.Version;
import seedu.docit.commons.exceptions.DataConversionException;
import seedu.docit.commons.util.ConfigUtil;
import seedu.docit.commons.util.StringUtil;
import seedu.docit.logic.Logic;
import seedu.docit.logic.LogicManager;
import seedu.docit.model.AddressBook;
import seedu.docit.model.AppointmentBook;
import seedu.docit.model.ArchivedAppointmentBook;
import seedu.docit.model.Model;
import seedu.docit.model.ModelManager;
import seedu.docit.model.ReadOnlyAddressBook;
import seedu.docit.model.ReadOnlyAppointmentBook;
import seedu.docit.model.ReadOnlyUserPrefs;
import seedu.docit.model.UserPrefs;
import seedu.docit.model.util.SampleDataUtil;
import seedu.docit.storage.AddressBookStorage;
import seedu.docit.storage.AppointmentBookStorage;
import seedu.docit.storage.ArchivedAppointmentBookStorage;
import seedu.docit.storage.JsonAddressBookStorage;
import seedu.docit.storage.JsonAppointmentBookStorage;
import seedu.docit.storage.JsonArchivedAppointmentBookStorage;
import seedu.docit.storage.JsonUserPrefsStorage;
import seedu.docit.storage.Storage;
import seedu.docit.storage.StorageManager;
import seedu.docit.storage.UserPrefsStorage;
import seedu.docit.ui.Ui;
import seedu.docit.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Doc'it ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        AddressBookStorage addressBookStorage = new JsonAddressBookStorage(userPrefs.getAddressBookFilePath());
        AppointmentBookStorage appointmentBookStorage =
            new JsonAppointmentBookStorage(userPrefs.getAppointmentBookFilePath());
        ArchivedAppointmentBookStorage archivedAppointmentBookStorage =
                new JsonArchivedAppointmentBookStorage(userPrefs.getArchivedAppointmentBookFilePath());
        storage = new StorageManager(addressBookStorage, appointmentBookStorage,
                archivedAppointmentBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br> The
     * data from the sample address book will be used instead if {@code storage}'s address book is not found, or an
     * empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyAddressBook> addressBookOptional;
        Optional<ReadOnlyAppointmentBook> appointmentBookOptional;
        Optional<ReadOnlyAppointmentBook> archivedAppointmentBookOptional;
        ReadOnlyAddressBook initialData;
        ReadOnlyAppointmentBook initialAppointmentData;
        ReadOnlyAppointmentBook initialArchivedAppointmentData;
        try {
            addressBookOptional = storage.readAddressBook();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AddressBook");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        }

        // Storage and Sample data for Appointments
        try {
            appointmentBookOptional = storage.readAppointmentBook(initialData);
            if (!appointmentBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AppointmentBook");
            }
            initialAppointmentData = appointmentBookOptional.orElseGet(SampleDataUtil::getSampleAppointmentBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AppointmentBook");
            initialAppointmentData = new AppointmentBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AppointmentBook");
            initialAppointmentData = new AppointmentBook();
        }

        //Storage and Data for Archived Appointments
        try {
            archivedAppointmentBookOptional = storage.readArchivedAppointmentBook(initialData);
            if (!archivedAppointmentBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AppointmentBook");
            }
            initialArchivedAppointmentData = archivedAppointmentBookOptional.orElseGet(
                    SampleDataUtil::getSampleArchivedAppointmentBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AppointmentBook");
            initialArchivedAppointmentData = new ArchivedAppointmentBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AppointmentBook");
            initialArchivedAppointmentData = new ArchivedAppointmentBook();
        }

        return new ModelManager(initialData, initialAppointmentData, initialArchivedAppointmentData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br> The default file path {@code
     * Config#DEFAULT_CONFIG_FILE} will be used instead if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path, or a new {@code UserPrefs}
     * with default configuration if errors occur when reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning(
                "UserPrefs file at " + prefsFilePath + " is not in the correct format. " + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting Doc'it " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Doc'it ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
