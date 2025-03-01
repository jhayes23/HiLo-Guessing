import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class HiLoController extends Application {
    private HiLoModel model;
    private HiLoView view;
    private int guess;
    private boolean correctGuess;
    private HiLoView.infoLabelStyle infoStyle;

    public HiLoController() {
        model = new HiLoModel();
        view = new HiLoView();

        view.resetAction(this::processReset);
        view.setGuessAction(this::processGuess);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HiLoController controller = new HiLoController();

        Scene scene = new Scene(controller.view.getParent(), 450, 300);
        primaryStage.setTitle("HiLo Guessing Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void processReset(ActionEvent event) {
        model.generateNewNumber();
        view.reset();
        correctGuess = false;
        guess = 0;
    }

    private void processGuess(ActionEvent event) {
        if (!correctGuess) {
            try {
                guess = Integer.parseInt(view.getGuess());
                if (guess < 1 || guess > 100) {
                    infoStyle = HiLoView.infoLabelStyle.INVALID_GUESS;
                } else if (model.previouslyGuessed(guess)) {
                    infoStyle = HiLoView.infoLabelStyle.PREV_GUESSED;
                } else {
                    correctGuess = model.checkNumber(guess);
                    if(correctGuess) {
                        infoStyle = HiLoView.infoLabelStyle.SUCCESS;
                        view.disableGuesses();
                    } else{
                        infoStyle = guess > model.getTarget() ? HiLoView.infoLabelStyle.HIGH:
                                HiLoView.infoLabelStyle.LOW;
                        view.updateMax(model.getMaxNumber());
                        view.updateMin(model.getMinNumber());
                    }
                    view.updateGuessCount(model.getNumberOfGuesses());

                }

                view.updateInfo(infoStyle);
            } catch (NumberFormatException e) {
                view.updateInfo(HiLoView.infoLabelStyle.INVALID_GUESS);
            }
        }

    }


}