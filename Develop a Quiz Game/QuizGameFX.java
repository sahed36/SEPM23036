import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.animation.*;
import javafx.util.Duration;

import java.sql.*;
import java.util.*;

public class QuizGameFX extends Application {

    private static final String URL = "jdbc:mysql://localhost:3306/quiz_game?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    private Stage stage;
    private List<Question> questions = new ArrayList<>();
    private int index = 0;
    private int score = 0;
    private String playerName;

    private Label timerLabel = new Label();
    private Timeline timeline;
    private int time = 15;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        showStartScene();
    }

    private void showStartScene() {
        TextField nameField = new TextField();
        Button startBtn = new Button("Start Quiz");

        VBox root = new VBox(10, new Label("Enter Your Name:"), nameField, startBtn);
        root.setAlignment(Pos.CENTER);

        startBtn.setOnAction(e -> {
            playerName = nameField.getText();
            loadQuestions();
            showQuizScene();
        });

        stage.setScene(new Scene(root, 400, 300));
        stage.setTitle("Quiz Game");
        stage.show();
    }

    private void loadQuestions() {
        questions.clear();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM questions ORDER BY RAND() LIMIT 5";
            ResultSet rs = con.prepareStatement(sql).executeQuery();

            while (rs.next()) {
                questions.add(new Question(
                        rs.getString("question"),
                        rs.getString("optionA"),
                        rs.getString("optionB"),
                        rs.getString("optionC"),
                        rs.getString("optionD"),
                        rs.getString("correctOption")
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        index = 0;
        score = 0;
    }

    private void showQuizScene() {
        Label qLabel = new Label();
        RadioButton a = new RadioButton();
        RadioButton b = new RadioButton();
        RadioButton c = new RadioButton();
        RadioButton d = new RadioButton();

        ToggleGroup tg = new ToggleGroup();
        a.setToggleGroup(tg);
        b.setToggleGroup(tg);
        c.setToggleGroup(tg);
        d.setToggleGroup(tg);

        Button nextBtn = new Button("Next");
        Button exitBtn = new Button("Exit");

        VBox root = new VBox(10, qLabel, a, b, c, d, timerLabel, nextBtn, exitBtn);
        root.setPadding(new Insets(20));

        loadQuestion(qLabel, a, b, c, d, tg);

        nextBtn.setOnAction(e -> {
            checkAnswer(tg);
            index++;
            if (index < questions.size()) {
                loadQuestion(qLabel, a, b, c, d, tg);
            } else {
                saveResult();
                showResultScene();
            }
        });

        exitBtn.setOnAction(e -> stage.close());

        stage.setScene(new Scene(root, 500, 400));
    }

    private void loadQuestion(Label q, RadioButton a, RadioButton b,
                              RadioButton c, RadioButton d, ToggleGroup tg) {
        Question qu = questions.get(index);
        q.setText((index + 1) + ". " + qu.q);
        a.setText("A. " + qu.a);
        b.setText("B. " + qu.b);
        c.setText("C. " + qu.c);
        d.setText("D. " + qu.d);
        tg.selectToggle(null);

        time = 15;
        timerLabel.setText("Time: " + time);

        if (timeline != null) timeline.stop();

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            time--;
            timerLabel.setText("Time: " + time);
            if (time == 0) {
                timeline.stop();
            }
        }));
        timeline.setCycleCount(15);
        timeline.play();
    }

    private void checkAnswer(ToggleGroup tg) {
        if (tg.getSelectedToggle() == null) return;
        RadioButton rb = (RadioButton) tg.getSelectedToggle();
        String ans = rb.getText().substring(0, 1);
        if (ans.equals(questions.get(index).correct)) score++;
    }

    private void saveResult() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO results(playerName, score) VALUES(?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, playerName);
            ps.setInt(2, score);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showResultScene() {
        Label result = new Label("Score: " + score + " / 5");
        Button restart = new Button("Restart");
        Button exit = new Button("Exit");

        VBox root = new VBox(15, result, restart, exit);
        root.setAlignment(Pos.CENTER);

        restart.setOnAction(e -> showStartScene());
        exit.setOnAction(e -> stage.close());

        stage.setScene(new Scene(root, 400, 300));
    }

    public static void main(String[] args) {
        launch(args);
    }

    class Question {
        String q, a, b, c, d, correct;

        Question(String q, String a, String b, String c, String d, String correct) {
            this.q = q;
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.correct = correct;
        }
    }
}
