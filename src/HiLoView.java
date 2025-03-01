import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class HiLoView {
    private StackPane primaryBox;
    private VBox vBox;
    private HBox hBox, hBox2;
    private BorderPane secondaryBox;
    private TextField numberField;
    private Button guessButton;
    private Button resetButton;
    private Label welcomeLabel;
    private Label textLabel;
    private Label guessLabel;
    private Label infoLabel;
    private Label guessCountLabel;
    private Label minLabel, maxLabel;
    private ImageView imageView, imageView2;

    public enum infoLabelStyle {
        SUCCESS("NICE! YOU GUESSED CORRECT"),
        PREV_GUESSED("THIS NUMBER HAS BEEN GUESSED ALREADY"),
        INVALID_GUESS("ENTER A VALID NUMBER BETWEEN 1 AND 100"),
        INCORRECT_GUESS("SORRY, GUESS AGAIN!"),
        HIGH("TOO HIGH"),
        LOW("TOO LOW");

        public final String text;

        infoLabelStyle(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public HiLoView() {
        Font font = Font.font("Georgia", FontWeight.EXTRA_BOLD, 15);
        numberField = new TextField();
        guessButton = new Button("Enter");
        guessButton.setFont(font);
        resetButton = new Button("Reset");
        resetButton.setFont(font);
        welcomeLabel = new Label("Welcome to the \nHi-Lo Guessing Game!");
        welcomeLabel.setTextFill(Color.ORCHID);
        Font welcomeFont = Font.loadFont(getClass().getResourceAsStream("./bin/SuperMario256.ttf"), 20);
        welcomeLabel.setFont(welcomeFont);
        welcomeLabel.setPadding(new Insets(5, 0, 0, 0));

        textLabel = new Label("Try to guess my hidden number that's \n\tbetween 1 and 100");
        textLabel.setFont(font);
        textLabel.setTextFill(Color.ORCHID);

        guessLabel = new Label("Enter your guess:");
        guessLabel.setFont(font);
        guessLabel.setTextFill(Color.ORCHID);

        infoLabel = new Label("Success!");
        infoLabel.setFont(font);
        infoLabel.setTextFill(Color.ORCHID);
        infoLabel.setVisible(false);

        guessCountLabel = new Label("Guess count: 0");
        guessCountLabel.setFont(font);
        guessCountLabel.setTextFill(Color.ORCHID);

        minLabel = new Label("Lo: 1");
        minLabel.setFont(font);
        minLabel.setTextFill(Color.ORCHID);

        maxLabel = new Label("Hi: 100");
        maxLabel.setFont(font);
        maxLabel.setTextFill(Color.ORCHID);

        hBox = new HBox(guessLabel, numberField, guessButton);
        hBox2 = new HBox(minLabel, maxLabel, guessCountLabel, resetButton);
        vBox = new VBox(welcomeLabel, textLabel, hBox, infoLabel);
        primaryBox = new StackPane();
        secondaryBox = new BorderPane();


        Image image = new Image("./bin/background.jpg");
        Image image2 = new Image("./bin/success.gif");
        imageView = new ImageView(image);
        imageView2 = new ImageView(image2);
        imageView.setFitHeight(300);
        imageView.setFitWidth(450);
        imageView2.setFitHeight(300);
        imageView2.setFitWidth(450);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(.3);

        imageView.setEffect(colorAdjust);
        imageView.setOpacity(.6);
        imageView.setPreserveRatio(false);
        imageView2.setVisible(false);
        primaryBox.getChildren().addAll(imageView, secondaryBox, imageView2);


        Insets insets = new Insets(0, 0, 25, 10);
        secondaryBox.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        numberField.setMinSize(80, 30);
        numberField.setAlignment(Pos.CENTER_RIGHT);
        guessButton.setMinSize(80, 40);
        hBox.setSpacing(10);
        infoLabel.setPadding(insets);

        secondaryBox.setBottom(hBox2);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setSpacing(15);
        resetButton.setMinSize(80, 40);
        resetButton.setViewOrder(-1);

        minLabel.setPadding(insets);
        maxLabel.setPadding(insets);
        HBox.setMargin(guessCountLabel, new Insets(0, 0, 25, 0));
        HBox.setMargin(resetButton, new Insets(0, 0, 25, 0));


    }

    public Parent getParent() {
        return primaryBox;
    }

    public void updateGuessCount(int guessCount) {
        guessCountLabel.setText("Guesses: " + guessCount);
    }

    public void updateInfo(infoLabelStyle infoLabelStyle) {
        if (!infoLabel.isVisible()) {
            infoLabel.setVisible(true);
        }
        if (infoLabelStyle == HiLoView.infoLabelStyle.SUCCESS) {
            imageView2.setVisible(true);
        }
        infoLabel.setText(infoLabelStyle.getText());
    }

    public void reset() {
        updateGuessCount(0);
        infoLabel.setVisible(false);
        imageView2.setVisible(false);
        updateMax(100);
        updateMin(1);

        guessButton.setDisable(false);
        numberField.setDisable(false);
        numberField.clear();
    }

    public void disableGuesses() {
        guessButton.setDisable(true);
        numberField.setDisable(true);
    }

    public void setGuessAction(EventHandler<ActionEvent> handler) {
        guessButton.setOnAction(handler);
        numberField.setOnAction(handler);
    }

    public String getGuess() {
        return numberField.getText().trim();
    }

    public void resetAction(EventHandler<ActionEvent> handler) {
        resetButton.setOnAction(handler);
    }

    public void updateMin(int minValue) {
        minLabel.setText("Lo: " + minValue);
    }

    public void updateMax(int maxValue) {
        maxLabel.setText("Hi: " + maxValue);
    }
}